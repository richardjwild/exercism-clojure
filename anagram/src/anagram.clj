(ns anagram
  (require [clojure.string :as str]))

(defn are-anagrams [s1 s2]
  (let [l1 (str/lower-case s1) l2 (str/lower-case s2)]
    (and
      (not= l1 l2)
      (= (sort l1) (sort l2)))))

(defn anagrams-for [word candidates]
  (vec (filter (partial are-anagrams word) candidates)))
