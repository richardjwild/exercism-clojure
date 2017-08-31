(ns grade-school)

(defn grade [db grade]
  (get db grade []))

(defn add [db name grade]
  (assoc db grade (conj (grade-school/grade db grade) name)))

(defn sorted [db]
  (into (sorted-map)
        (apply hash-map
               (mapcat (fn [[grade names]] [grade (sort names)]) db))))