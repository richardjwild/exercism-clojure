(ns etl
  (:require [clojure.string :as str]))

(defn to-list-lower-case [key value]
  (list (str/lower-case value) key))

(defn flatten [map-entry]
  (let [[key values] map-entry]
    (mapcat (partial to-list-lower-case key) values)))

(defn transform [input]
  (apply sorted-map (mapcat flatten input)))