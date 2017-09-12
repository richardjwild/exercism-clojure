(ns binary-search)

(defn end [numbers]
  (-> numbers count dec))

(defn middle
  ([start-pos end-pos]
   (int (/ (+ start-pos end-pos) 2)))
  ([numbers]
   (middle 0 (end numbers))))

(defn search-for [to-find numbers]
  (loop [start-pos 0 end-pos (end numbers)]
    (if (> start-pos end-pos)
      (throw (Exception. "not found"))
      (let [middle-pos (middle start-pos end-pos) middle-val (nth numbers middle-pos)]
        (if (= middle-val to-find)
          middle-pos
          (recur (if (< middle-val to-find) (inc middle-pos) start-pos)
                 (if (< middle-val to-find) end-pos (dec middle-pos))))))))
