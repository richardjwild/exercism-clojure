(ns gigasecond
  (require [clj-time.core :as t]))

(defn add-gigasecond [date]
  (t/plus date (t/seconds 1e9)))

(defn to-list [date]
  ((juxt t/year t/month t/day) date))

(defn from [year month day]
  (-> (t/date-time year month day)
      add-gigasecond
      to-list))
