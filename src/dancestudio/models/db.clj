(ns dancestudio.models.db
(:require [clojure.java.jdbc :as sql])
(:import java.sql.DriverManager)
(:import java.util.Date))

(def db
    {:classname "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname "//localhost:5432/dancestudio"
     :username "igor.bajovic"
     :password "ivana"})

(defn insert-gymnastichall [id name]
  (sql/insert! db :gymnastichall
                {:id id
                 :name name}))

(defn update-gymnastichall [id name]
  (sql/update! db :gymnastichall
               {:name name}
               ["id = ?" id]))

(defn delete-gymnastichall [id]
 (sql/delete! db :gymnastichall ["id = ?" id]))

(defn read-gymnastichalls []
 (sql/query db
 ["select * from gymnastichall order by id"]))

 
(defn insert-danceprogram [id name]
  (sql/insert! db :danceprogram
                {:id id
                 :name name}))

(defn update-danceprogram [id name]
  (sql/update! db :danceprogram
               {:name name}
               ["id = ?" id]))

(defn delete-danceprogram [id]
 (sql/delete! db :danceprogram ["id = ?" id]))

(defn read-danceprograms []
 (sql/query db
 ["select * from danceprogram order by id"]))


(defn insert-member [id name surname danceprogram_id gymnastichall_id lesonsnumbermonth coachcommitment]
  (sql/insert! db  :member
               {:id id
                :name name
                :surname surname
                :danceprogram_id danceprogram_id
                :gymnastichall_id gymnastichall_id
				:lesonsnumbermonth lesonsnumbermonth
				:coachcommitment coachcommitment}))

(defn update-member [id name surname danceprogram_id gymnastichall_id lesonsnumbermonth coachcommitment]
  (sql/update! db :member
                {:name name
                 :surname surname
                 :danceprogram_id danceprogram_id
                 :gymnastichall_id gymnastichall_id
				 :lesonsnumbermonth lesonsnumbermonth
				 :coachcommitment coachcommitment} ["id = ?" id]))
				 
				 
(defn update-member-fee [id fee]
  (sql/update! db :member
                {:fee fee} ["id = ?" id]))				 

(defn delete-member [id]
 (sql/delete! db :member ["id = ?" id]))

(defn read-members []
  (sql/query db
   ["select m.id, m.name as member_name, m.surname, g.name as gymnastichall_name, d.name as danceprogram_name, m.lesonsnumbermonth, m.coachcommitment, m.fee from (member m inner join gymnastichall g on m.gymnastichall_id = g.id) inner join danceprogram d on m.danceprogram_id = d.id"] ))   
 
(defn  read-members-short []
  (sql/query db
   ["select id,name,surname from member"]))









