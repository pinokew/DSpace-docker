import sys
import os
import logging
from src.config import setup_logging
from src.dspace import DSpaceClient

def main():
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s [%(levelname)s] [%(name)s] %(message)s',
        datefmt='%Y-%m-%d %H:%M:%S',
        force=True,
        handlers=[logging.StreamHandler(sys.stdout)]
    )
    logger = logging.getLogger("WriteTest")
    
    logger.info("=== KDV INTEGRATOR: FULL CYCLE TEST ===")
    
    client = DSpaceClient()
    
    # 1. Login
    if not client.login():
        sys.exit(1)
        
    # 2. Get Collection
    collections = client.get_collections()
    if not collections:
        logger.error("No collections found!")
        sys.exit(1)
        
    target_collection = collections[0]
    logger.info(f"Target Collection: '{target_collection['name']}'")
    
    # 3. Create Item
    wsi = client.create_workspace_item(target_collection['uuid'])
    if not wsi:
        sys.exit(1)
    
    wsi_id = wsi['id']
    
    # 4. ADD METADATA
    logger.info("--- Adding Metadata ---")
    metadata = [
        {'key': 'dc.title', 'value': 'Test Book with License'},
        {'key': 'dc.date.issued', 'value': '2026'},
        {'key': 'dc.type', 'value': 'Book'}
    ]
    
    if not client.add_metadata(wsi_id, metadata):
        logger.error("Failed to add metadata.")
        sys.exit(1)
    
    # 5. Upload File
    logger.info("--- Uploading File ---")
    dummy_file = "test_upload.txt"
    with open(dummy_file, "w") as f:
        f.write("Test content for KDV Integrator.")
    
    upload_ok = client.upload_bitstream(wsi_id, dummy_file)
    if os.path.exists(dummy_file):
        os.remove(dummy_file)
        
    if not upload_ok:
        sys.exit(1)

    # 6. GRANT LICENSE (NEW STEP)
    logger.info("--- Granting License ---")
    if not client.grant_license(wsi_id):
        logger.error("Failed to grant license.")
        sys.exit(1)
        
    # 7. PUBLISH
    logger.info("--- Attempting to Publish ---")
    publish_ok = client.publish_item(wsi_id)
    
    if publish_ok:
        logger.info("\n🎉 SUCCESS: Item fully processed (Metadata + File + License + Deposit)!")
        sys.exit(0)
    else:
        logger.error("\n⚠️ Failed to publish.")
        sys.exit(1)

if __name__ == "__main__":
    main()