(ns dancestudio.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))


(defn common [& body]
  (html5
    [:head
     [:title "Dance is life!"]
     (include-css "/css/screen.css")
      ]
    [:body body
     (include-js "//code.jquery.com/jquery-1.10.1.min.js"
                 "/js/gymnastichalls.js"
                 "/js/danceprograms.js"
                 "/js/members.js")]))
				 
