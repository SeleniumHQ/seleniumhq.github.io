# Running tests from Selenium Python examples

#### 1. Clone this repository

```
git clone https://github.com/SeleniumHQ/seleniumhq.github.io.git
```

#### 2. Navigate to `python` directory

```
cd seleniumhq.github.io/examples/python
```

#### 3. Create a virtual environment

- On Windows:

```
py -m venv venv
venv\Scripts\activate
```

- On Linux/Mac:

```
python3 -m venv venv
source venv/bin/activate
```

#### 4. Install dependencies:

```
pip install -r requirements.txt
```

> for help, see: https://packaging.python.org/en/latest/tutorials/installing-packages

#### 5. Run tests

- Run all tests with the default Python interpreter:

```
pytest
```

- Run all tests with every installed/supported Python interpreter:

```
tox
```

> Please have some patience - If you are doing it for the first time, it will take a little while to download the browser drivers

- Run a specific example:

```
pytest path/to/test_script.py
```

> Make sure to replace `path/to/test_script.py` with the path and name of the example you want to run
