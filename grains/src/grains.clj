(ns grains)

(defn raise-2 [x]
  (.pow (BigDecimal/valueOf 2) x))

(defn square [x]
  (-> x dec raise-2 bigint))

(defn total []
  (bigint (reduce + (map raise-2 (range 64)))))