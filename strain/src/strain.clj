(ns strain)

(defn retain [pred seq]
  (loop [result [], s seq]
    (if (empty? s)
      result
      (let [[item & rest] s]
        (recur (if (pred item) (conj result item) result) rest)))))

(defn discard [pred seq]
  (retain #(not (pred %)) seq))
