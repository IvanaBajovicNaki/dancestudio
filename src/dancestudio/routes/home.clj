(ns dancestudio.routes.home
  (:require [compojure.core :refer :all]
            [dancestudio.views.layout :as layout]
            [hiccup.form :refer :all]
            [dancestudio.models.db :as db]
            [hiccup.page :as hic-p]))

(defn home []
(layout/common
 [:h1.homeH "Welcome to dance studio application"]
 [:h2.homeP "Choose one of following options:"]
 [:input.homeBtn {:type "a" :value "Work with gymnastic halls" :onclick "window.location.href = '/gymnastichall'"}][:br]
 [:input.homeBtn {:type "a" :value "Work with dance programs" :onclick "window.location.href = '/danceprogram'"}] [:br]
 [:input.homeBtn {:type "a" :value "Work with members" :onclick "window.location.href = '/member'"}]))

(defroutes home-routes
  (GET "/" [] (home)))





