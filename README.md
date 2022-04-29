# CS-411-project

to run the database code: 
  1. Make sure to download the database, the .csv files (excel sheets), and all other files.
  2. before establishing the connction of the database on MySQL, rightclick on the MySql root connection, click on the edit connection option, click advanced, the add the following line to the OTHERS box: OPT_LOCAL_INFILE=1
  3. after establishing the connection, open the root, at the bottom of the database code you need to copy the paths of each .csv file and paste it in its corresponding line of code.
  4. make sure the path looks similar to this:
               'C:\\Users\\lamya\\Desktop\\testing\\allergy.csv'
           with one quoutation mark on each side and double slashes instead of ones. (view the raw code of this readme to get a better understanding)
  5. The .jar file found in the lib folder must be added to the referenced library folder.
  6. Connect the database to the MySQL server using the terminal.
  7. the following lines of code are run in the JavaMySQLTest to connect the mysql database we created to the java file:
      String url = "jdbc:mysql://localhost:3306/test1";
		  String username = "root";
		  String password = "password"; 
      The username and password fields might not be applicable to all users. First, check via MySQL terminal to see which is most apporapriate. 
      
  8. Run the java gui file.
