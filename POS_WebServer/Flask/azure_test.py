import pyodbc
server = '6613posserver.database.windows.net'
database = '6613posdb'
username = 'admin1234'
password = 'password1234!'
driver= '{ODBC Driver 13 for SQL Server}'
cnxn = pyodbc.connect('DRIVER='+driver+';PORT=1433;SERVER='+server+';PORT=1443;DATABASE='+database+';UID='+username+';PWD='+ password)
cursor = cnxn.cursor()
cursor.execute("SELECT * FROM Persons")
row = cursor.fetchall()
for item in row:
    print item

#    Data Source=tcp:6613posserver.database.windows.net,1433;Initial Catalog=6613posdb;User ID=admin1234@6613posserver;Password=password1234!
