(ns dancestudio.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [dancestudio.routes.home :refer [home-routes]]
            [dancestudio.routes.gymnastichalls :refer [gymnastichall-routes]]
            [dancestudio.routes.danceprograms :refer [danceprogram-routes]]
            [dancestudio.routes.members :refer [member-routes]]))

(defn init []
  (println "dancestudio is starting"))

(defn destroy []
  (println "dancestudio is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes member-routes danceprogram-routes gymnastichall-routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))


