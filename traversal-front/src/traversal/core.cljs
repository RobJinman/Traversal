(ns traversal.core
  (:require [goog.dom :as gdom]
            [goog.string :as gstring]
            [goog.string.format]
            [reagent.core :as reagent]
            [re-frame.core :refer [reg-event-db
                                   reg-sub
                                   dispatch
                                   dispatch-sync
                                   subscribe]]
            [clojure.set]))

(enable-console-print!)


(defn new-state
  []
  {:counter 0})


;; -- State Updater Functions----------------------------------------------



;; -- Event Handlers ------------------------------------------------------

(reg-event-db
 :initialise
 (fn [state _]
   (new-state)))

(reg-event-db
 :inc-counter
 (fn [state _]
   (update state :counter inc)))


;; -- Subscription Handlers -----------------------------------------------

(reg-sub
 :counter
 (fn [state _]
   (:counter state)))


;; -- View Components -----------------------------------------------------

(defn main-view
  []
  (let [counter (subscribe [:counter])]
    (fn []
      [:div.rl-traversal
       [:span.rl-counter @counter]
       [:button {:onClick #(dispatch [:inc-counter])} "Click Me!"]])))

(defn ^:export run
  []
  (dispatch-sync [:initialise])
  (reagent/render [main-view]
                  (gdom/getElement "traversal")))
