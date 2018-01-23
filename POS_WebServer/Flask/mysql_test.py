import mysql.connector

cnx = mysql.connector.connect(user='root', password='password1234!',
                              host='127.0.0.1',
                              database='test_db')

cursor = cnx.cursor()

query = ("SELECT * FROM mytable ")

cursor.execute(query)

for cur in cursor:
  print(cur)

cursor.close()

cnx.close()
print"Completed"
