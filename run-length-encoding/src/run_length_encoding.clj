(ns run-length-encoding
  (require [clojure.string :as str]))

(defn- to-data-element [run]
  (let [size (count run), [character] run]
    (if (= 1 size)
      (format "%c" character)
      (format "%d%c" size character))))

(defn run-length-encode [string]
  (->> string
       (partition-by identity)
       (map to-data-element)
       (apply str)))

(defn- repeat-character [data-element]
  (repeat (data-element :quantifier) (data-element :character)))

(defn- extract-quantifier [data-element]
  (let [quantifier (re-find #"[0-9]*" data-element)]
    (if (empty? quantifier) "1" quantifier)))

(defn- to-map [data-element]
  (hash-map :quantifier (-> data-element extract-quantifier read-string)
            :character (last data-element)))

(defn- break-into-data-elements [encoded-string]
  (re-seq #"[0-9]*[A-Za-z ]{1}" encoded-string))

(defn run-length-decode [encoded-string]
  (->> encoded-string
       break-into-data-elements
       (map to-map)
       (mapcat repeat-character)
       (apply str)))
