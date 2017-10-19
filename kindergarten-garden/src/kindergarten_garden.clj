(ns kindergarten-garden
  (require [clojure.string :as str]))

(def kids
  ["Kincaid" "Charlie" "Alice" "Bob" "David" "Eve" "Fred" "Ginny" "Harriet" "Ileana" "Joseph" "Larry"])

(def plants
  {\G :grass, \C :clover, \R :radishes, \V :violets})

(defn- plants-for-student [student [plant1 plant2] [plant3 plant4]]
  (->> (list plant1 plant2 plant3 plant4)
       (map plants)
       (apply vector)
       (list student)))

(defn- garden- [[row1 row2] students]
  (apply hash-map
         (mapcat plants-for-student
                 (into (sorted-set) students)
                 (partition-all 2 row1)
                 (partition-all 2 row2))))

(defn- to-keyword [name]
  (-> name
      str/lower-case
      keyword))

(defn garden
  ([rows]
   (garden rows kids))
  ([rows names]
   (garden- (str/split-lines rows) (map to-keyword names))))
