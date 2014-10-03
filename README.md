A Clojure web application for keeping evidences of dance studio's business, more preciesely for keeping evidences of gymnastic halls, dance programs and members. 

This application allows insertion of new gymnastic hall, updating data for existing gymnastic hall, deleting existing gymnastic hall and preview of all available gymnastic halls. 
1. Preview of all existing gymnastic halls is tabular. One review table row represents one gymnastic hall.
2. Insertion of new gymnastic hall is done by entering data in form fields and selecting option "Save gymnastic hall". After selecting this option new gymnastic hall will be displayed in review table. 
3. Updating data for existing gymnastic hall is done through review table. User needs to double click on the field of table row that wants to modify. After data modification user need to press enter and select option "Update" of current table row.
4. Deleting existing gymnastic hall is done through review table. User needs to select option "Delete" of table row witch wants to delete.

This application allows insertion of new dance program, updating data for existing dance program, deleting existing dance program and preview of all available dance programs. 
1. Preview of all existing dance programs is tabular. One review table row represents one dance program.
2. Insertion of new dance program is done by entering data in form fields and selecting option "Save dance program". After selecting this option new dance program will be displayed in review table. 
3. Updating data for existing dance program is done through review table. User needs to double click on the field of table row that wants to modify. After data modification user need to press enter and select option "Update" of current table row.
4. Deleting existing dance program is done through review table. User needs to select option "Delete" of table row witch wants to delete.

This application allows insertion of new member, deleting existing member and preview of all members. 
1. Preview of all members is tabular. One review table row represents one member.
2. Insertion of new member is done by entering data in form fields and selecting option "Save member". After selecting this option new member will be displayed in review table. 
3. Deleting existing member is done through review table. User needs to select option "Delete" of table row witch wants to delete.

One feature of this application is also suggesting member's fee according to its data and requests (number of lesons per month, coach's commitment). In this feature (calculation) application relies on the Euclidean-distance algorithm. Euclidean distance or Euclidean metric is the "ordinary" distance between two points that one would measure with a ruler, and is given by the Pythagorean formula. Suggesting member's fee is done through review table. User needs to select option "Calculate fee" of table row/member whose fee wants to calculate. 

The application is written in Clojure. This application is designed for learning Clojure.

The application consists of 4 pages:

Home page - serves as a starting point and displays available three options (links).
Gymnastichalls page - preview, insertion, updating and gymnastic halls deleting.
Danceprograms page - preview, insertion, updating and dance programs deleting.
Members page - preview, insertion, members deleting and member's fee suggesting.

Usage

Download and install Leiningen, and then download and install PostgreSQL (link is : http://www.enterprisedb.com/products-services-training/pgdownload#windows). It's necessary to start PostgreSQL before running the application, import dancestudio database if does not exists and connect to it.

To start the application navigate to the root folder of "dancestudio" application, enter lein deps (only if the application is running on current machine for the first time) and enter lein ring server.

References

Practical Clojure, Luke VanderHart and Stuart Sierra, Clojure Programming, Chas Emerick, Brian Carper and Chrisophe Grand and Web Development with Clojure, Build Bulletproof Web Apps with Less Code, Dmitri Sotnikov

License

Distributed under the Eclipse Public License, the same as Clojure.
