import MySQLdb
import mysql.connector
from mysql.connector import Error

def db_query(query,qtype):
    """ Connect to MySQL database """
    try:
        conn = mysql.connector.connect(host='localhost',
                                       database='pos_db',
                                       user='root',
                                       password='pos1')
        if conn.is_connected():
            #print('Connected to MySQL database')
            cursor = conn.cursor()
            if(qtype == "fetch"):
                cursor.execute(query)
                row = cursor.fetchall()
                string_response = ""
                while row is not None:
                    #print(row)
                    for item in row:
                        #print(item)
                        for i in item:
                            #print (i)
                            string_response = string_response + str(i) 
                            string_response = string_response + ','
                        string_response = string_response[:-1] + ('\n')       
                    row = cursor.fetchone()
                    print string_response[:-1]
            elif(qtype=="insert"):
                cursor.execute(query)
                conn.commit()
    except Error as e:
       print("error")
       print(e)
 
    finally:
        cursor.close()
        conn.close()
 


if __name__ == '__main__':
    db_query("select * from products1","insert")
