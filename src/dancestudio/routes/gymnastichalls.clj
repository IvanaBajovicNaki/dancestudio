(ns dancestudio.routes.gymnastichalls
  (:require [compojure.core :refer :all]
            [dancestudio.views.layout :as layout]
            [hiccup.form :refer :all]
            [dancestudio.models.db :as db]
            [hiccup.page :as hic-p]))

(defn all-gymnastichalls
  []
  (let [all-gym (db/read-gymnastichalls)]
  [:div
	 [:h1.title "Review of all gymnastichalls"]
     [:table
      [:tr [:th "Id"] [:th "Description"] [:th "Delete"] [:th "Update"]]
      (for [gym all-gym]
        [:tr
         [:td (:id gym)]
         [:td.editable (:name gym)]
         [:td [:input.deleteGymnastichall {:type "submit" :value "Delete"}]]
         [:td [:input.updateGymnastichall {:type "submit" :value "Update"}]]])]]))

(defn add-gymnastichall []
  [:form.newForm {:action "/gymnastichall" :method "POST"}
   [:h2.title2 "Insert data for a new gymnastichall:"]
   [:h3.newH "Id:&nbsp;&nbsp;" [:input.newTxt {:type "text" :name "id"}]]
   [:h3.newH "Description: " [:input.newTxt {:type "text" :name "name"}]]
   [:h3.newH [:input.newBtn {:type "submit" :value "Save gymnastic hall"}]]])


(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-gymnastichalls)
   [:hr]
   (add-gymnastichall)))

(defn save-gymnastichall [id name]
(cond
(empty? id)
(home id name "Id is required field!")
(empty? name)
(home id name "Name is required field!")
:else
(do(try 
   (let [id (Integer/parseInt id)]
   (db/insert-gymnastichall id name)
   (home))
   (catch Exception e (home id name "Id must be number!"))))))

(defn remove-gymnastichall [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-gymnastichall id)
    )))

(defn update-gymnastichall [id name]
  (do (let [id (Integer/parseInt id)]
    (db/update-gymnastichall id name))))

(defroutes gymnastichall-routes
  (GET "/gymnastichall" [id name error] (home id name error))
  (POST "/gymnastichall" [id name] (save-gymnastichall id name))
  (DELETE "/remove-gymnastichall" [id] (remove-gymnastichall id))
  (POST "/update-gymnastichall" [id name] (update-gymnastichall id name)))





