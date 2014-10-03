(ns dancestudio.routes.danceprograms
  (:require [compojure.core :refer :all]
            [dancestudio.views.layout :as layout]
            [hiccup.form :refer :all]
            [dancestudio.models.db :as db]
            [hiccup.page :as hic-p]))

(defn all-danceprograms
  []
  (let [all-dancepr (db/read-danceprograms)]
   [:div
	 [:h1.title "Review of all programs"]
	 [:table
      [:tr [:th "Id"] [:th "Description"] [:th "Delete"] [:th "Update"]]
      (for [dancepr all-dancepr]
        [:tr [:td (:id dancepr)] [:td.editable (:name dancepr)] [:td [:input.deleteDanceprogram {:type "submit" :value "Delete"}]]
          [:td [:input.updateDanceprogram {:type "submit" :value "Update"}]]])]]))


(defn add-danceprogram []
  [:form.newForm {:action "/danceprogram" :method "POST"}
  [:h1.title2 "Insert data for a new danceprogram:"]
   [:h3.newH "Id:&nbsp;&nbsp;" [:input.newTxt {:type "text" :name "id"}]]
   [:h3.newH "Description: " [:input.newTxt {:type "text" :name "name"}]]
   [:h3.newH [:input.newBtn {:type "submit" :value "Save dance program"}]]])


(defn home [& [id name error]]
  (layout/common
   [:p error]
   (all-danceprograms)
   [:hr]
   (add-danceprogram)))

(defn save-danceprogram [id name]
(cond
(empty? id)
(home id name "Id is required field!")
(empty? name)
(home id name "Name is required field!")
:else
(do(try 
   (let [id (Integer/parseInt id)]
   (db/insert-danceprogram id name)
   (home))
   (catch Exception e (home id name "Id must be number!"))))))

(defn remove-danceprogram [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-danceprogram id))))

(defn update-danceprogram [id name]
  (do (let [id (Integer/parseInt id)]
    (db/update-danceprogram id name))))


(defroutes danceprogram-routes
  (GET "/danceprogram" [id name error] (home id name error))
  (POST "/danceprogram" [id name] (save-danceprogram id name))
  (DELETE "/remove-danceprogram" [id] (remove-danceprogram id))
  (POST "/update-danceprogram" [id name] (update-danceprogram id name)))





