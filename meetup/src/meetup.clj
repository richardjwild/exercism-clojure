(ns meetup
  (require [clj-time.core :as t]))

(def week-days
  {:monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 :saturday 6 :sunday 7})

(def one-week (t/days 7))

(defn days-until-first [day month-start]
  (t/days
    (mod (- (day week-days) (t/day-of-week month-start)) 7)))

(defn dates-in-month [month-start month-end day]
  (let [first-date (t/plus- month-start (days-until-first day month-start))]
    (loop [dates [] date first-date]
      (if (t/after? date month-end)
        dates
        (recur (conj dates date) (t/plus- date one-week))))))

(defn is-teenth [date]
  (let [day (t/day date)]
    (and (>= day 13) (<= day 19))))

(def selectors
  {:first #(first %1)
   :second #(nth %1 1)
   :third #(nth %1 2)
   :fourth #(nth %1 3)
   :last #(last %1)
   :teenth #(first (filter is-teenth %1))})

(defn meetup [month year weekday which-one]
  (let [month-start (t/local-date year month 1)
        month-end (t/last-day-of-the-month- month-start)
        dates (dates-in-month month-start month-end weekday)
        date ((get selectors which-one) dates)]
    (vector (t/year date) (t/month date) (t/day date))))
