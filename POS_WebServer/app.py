from flask import Flask, request, render_template
import mysql.connector
from mysql.connector import Error

app = Flask(__name__)

@app.route('/db_query/<query>/<qtype>')
def query(query,qtype):
        return db_query(query,qtype)
    
@app.route('/db_query/<query>')
def query_test(query):
    # show the user profile for that user
    if (query == "test"):
        return "Connected"
    else:
        return "No Query"


@app.route('/')
def index():
    return 'POS Web Server ECNG 6613'


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
                    return string_response[:-1]
            elif(qtype=="insert"):
                cursor.execute(query)
                conn.commit()
                return ("successful")
    except Error as e:
       print(e)     
       return("error")
       
 
    finally:
        cursor.close()
        conn.close()

if __name__ == '__main__':
    app.run(debug=True, host='192.168.1.2')
