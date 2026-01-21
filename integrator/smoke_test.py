import sys
import os

# Додаємо поточну папку в шлях імпорту
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from src.koha import KohaClient
from src.dspace import DSpaceClient

def main():
    print("========================================")
    print("   KDV INTEGRATOR: SMOKE TEST")
    print("========================================")
    
    # 1. Test Koha
    print("\n--- Testing Koha Connection ---")
    koha = KohaClient()
    koha_ok = koha.test_connection()
    
    # 2. Test DSpace
    print("\n--- Testing DSpace Connection ---")
    dspace = DSpaceClient()
    dspace_ok = dspace.login()
    if dspace_ok:
        dspace.check_status()
    
    # Summary
    print("\n========================================")
    if koha_ok and dspace_ok:
        print("🎉 SUCCESS: Both systems are reachable!")
    else:
        print("⚠️ FAILURE: Check logs above.")
    print("========================================")

if __name__ == "__main__":
    main()