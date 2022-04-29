# CS-411-project

to run the database code: 
  1. download the database and the .csv files (excel sheets).
  2. before establishing the connction of the database on MySQL, rightclick on the MySql root connection, click on the edit connection option, click advanced, the add the following line to the OTHERS box: OPT_LOCAL_INFILE=1
  3. after establishing the connection, open the root, at the bottom of the database code you need to copy the paths of each .csv file and past it in its corresponding line of code.
  4. make sure the path looks similar to this:
               'C:\\Users\\lamya\\Desktop\\testing\\allergy.csv'
           with one quoutation mark on each side and double slashes instead of ones. (view the raw code of this readme to get a better understanding)
  5. run the whole code then you can select which table you would like to view by running the specific line the table is viewed in. 
            for example, running this line will only display the allergy table:
                SELECT * FROM allergy;
