FROM python:3.11-slim

WORKDIR /app

# Ця змінна змушує Python виводити логи в консоль миттєво (без буферизації)
ENV PYTHONUNBUFFERED=1

# Копіюємо файл залежностей
COPY requirements.txt .

# Встановлюємо бібліотеки
RUN pip install --no-cache-dir -r requirements.txt

# Копіюємо весь код
COPY . .

# Команда за замовчуванням
CMD ["python", "smoke_test.py"]