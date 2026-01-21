FROM python:3.11-slim

WORKDIR /app

# Копіюємо файл залежностей
COPY requirements.txt .

# Встановлюємо бібліотеки
RUN pip install --no-cache-dir -r requirements.txt

# Копіюємо весь код
COPY . .

# Команда за замовчуванням - запуск тесту
CMD ["python", "smoke_test.py"]