(ns dancestudio.routes.members
  (:require [compojure.core :refer :all]
            [dancestudio.views.layout :as layout]
            [hiccup.form :refer :all]
            [dancestudio.models.db :as db]
            [hiccup.page :as hic-p]))

;;util ethods

(defn parse-double [s]
  (if (and (string? s) (re-matches #"\s*[+-]?\d+(\.\d+(M|M|N)?)?\s*" s))
    (read-string s)))
	
(defn square [x]
  (* x x))
  
;;Algorithm for calculating/suggesting member's fee according to predefined data or rules

(def sample-data [[1000 10 0.1], [2000 20 0.2],[3000 30 0.3] [4000 30 0.6] [5500 30 0.9] [3000 20 0.6] [3600 20 0.9] [2000 10 0.6] [2500 10 0.9]])

(defn euclidean-distance
  "This function returns the euclidean distance between points x and y where x and y are vectors"
  [p q]
  (Math/sqrt
    (->> (map - p q)
         (map square)
         (reduce +))))

(def distance-fn (fn [v1 v2] (euclidean-distance v1 v2)))

(defn distance
  "This function takes the distance in each axis, square them and add them together"
  [x y]
  (let [[a b] x
        [c d] y]
    (Math/sqrt
      (+ (square (- a c)) (square (- b d))))))

(defn distance-normalized
  "This function gives higher values for items that are similar"
  [x y]
  (/ 1 (+ 1 (euclidean-distance x y))))
	
(defn majority-label
  "This function returns the majority-label from the data set"
  [results]
  (let [labels (map first results)]
    (first
     (->> labels
          (partition-by identity)
          (sort-by count)
          ;(reverse)
         (flatten)))))
		 		
(defn knn [sample data k]
  "This function returns the k nearest neighbors"
  (let [distance-fn (partial distance-normalized sample)
        results (map #(vector (first %) (distance-fn (second %))) data)]
   (take k (sort-by second  > results))))	
  
(defn classify
  [sample data k]
  (let [kn (knn sample data k)]
    (majority-label kn)))
	
		
;;General functions for manipulating with domain data	
		
(defn all-members
  []
  (let [all-memb (db/read-members)]
  [:div
	 [:h1.title "Review of all members"]
     [:table
      [:tr [:th "Id"] [:th "First Name"] [:th "Last Name"] [:th "Gymnastic hall"][:th "Dance program"] [:th "Number of lesons per month"] [:th "Coach's commitment (%)"] [:th "Suggested fee"] [:th ""] [:th ""]]
      (for [memb all-memb]
        [:tr [:td (:id memb)] [:td (:member_name memb)] [:td (:surname memb)] [:td (:gymnastichall_name memb)][:td (:danceprogram_name memb)][:td (:lesonsnumbermonth memb)] [:td (:coachcommitment memb)] [:td (:fee memb)]
         [:td [:input.updateMember {:type "submit" :value "Calculate fee"}]]
		 [:td [:input.deleteMember {:type "submit" :value "Delete"}]]])]]))

(defn add-member
  []
  (let [all-gymnastichalls (db/read-gymnastichalls)
        all-danceprograms (db/read-danceprograms)]
	[:form.newForm
	[:h1.title2 "Insert data for a new member:"]
    [:h3.newH "Id:&nbsp;&nbsp; " [:input.newTxt {:type "text" :name "id" :id "memberId"}]]
    [:h3.newH "First name: " [:input.newTxt {:type "text" :name "name" :id "memberName"}]]
    [:h3.newH "Last name: " [:input.newTxt {:type "text" :name "surname" :id "memberSurname"}]]
							 
    [:h3.newH "Gymnastic hall: " [:select.selectText {:id "gymnastichalls"} (for [gym all-gymnastichalls]
                             [:option {:id (:id gym) :value (:id gym) } (:name gym)])] ]
							 
    [:h3.newH "Dance program: " [:select.selectText {:id "danceprograms"}(for [dancepr all-danceprograms]
                             [:option {:id (:id dancepr) :value (:id dancepr) } (:name dancepr)])] ]
							 
	[:h3.newH "Number of lesons per month (1-30): " [:input.newTxt {:type "text" :name "lesonsnumbermonth" :id "memberLesonsnumbermonth"}]]
	
	[:h3.newH "Coach's commitment in % (0-100): " [:input.newTxt {:type "text" :name "coachcommitment" :id "memberCoachcommitment"}]]
	
    [:h3.newH [:input.insertMember {:type "submit" :value "Save member"}]]]))

(defn home [& [id name surname gymnastichall_id danceprogram_id lesonsnumbermonth coachcommitment error]]
  (layout/common
   [:p error]
   (all-members)
   [:hr]
   (add-member)))

(defn save-member [id name surname gymnastichall_id danceprogram_id lesonsnumbermonth coachcommitment]
  (do (let [id (Integer/parseInt id)
        gymnastichall_id (Integer/parseInt gymnastichall_id)
        danceprogram_id (Integer/parseInt danceprogram_id)
		lesonsnumbermonth (Integer/parseInt lesonsnumbermonth)
		coachcommitment (Integer/parseInt coachcommitment)]
    (db/insert-member id name surname danceprogram_id gymnastichall_id lesonsnumbermonth coachcommitment))))

(defn remove-member [id]
 (do (let [id (Integer/parseInt id)]
    (db/delete-member id))))

(defn calculate-fee [lesonsnumbermonth coachcommitment]
  (let [lid (parse-double lesonsnumbermonth) tid (parse-double coachcommitment)]
	(let [calculated-fee (classify (vector lid tid)(into [] (map #(vector (first %) (vector (second %)(last %))) sample-data)) 3)] 
		calculated-fee)))
		
(defn update-member [id lesonsnumbermonth coachcommitment]
  (do (let [id (Integer/parseInt id) fee (calculate-fee lesonsnumbermonth coachcommitment)]
    (println " ....................  fee is " fee)
	(db/update-member-fee id fee))))
		
(defroutes member-routes
  (GET "/member" [] (home))
  (POST "/save-member" [id name surname gymnastichall_id danceprogram_id lesonsnumbermonth coachcommitment] (save-member id name surname gymnastichall_id danceprogram_id lesonsnumbermonth coachcommitment))
  (DELETE "/remove-member" [id] (remove-member id))
  (POST "/update-member" [id lesonsnumbermonth coachcommitment] (update-member id lesonsnumbermonth coachcommitment)))

	 





