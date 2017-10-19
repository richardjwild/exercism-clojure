(ns spiral-matrix)

(defn squared [n]
  (* n n))

(defn indices [matrix-size]
  (range 1 (inc (squared matrix-size))))

(defn step-range [from step]
  (map (partial + from) (map (partial * step) (range))))

(defn mapping [outer-size]
  (loop [spiral-size outer-size, direction 1, mapping '(), last-value 0]
    (let [next-value (+ last-value direction),
          column-step (* outer-size direction)]
      (if (= 1 spiral-size)
        (concat mapping (list next-value))
        (let [row (take spiral-size (step-range next-value direction)),
              column (take (dec spiral-size) (step-range (+ (last row) column-step) column-step))]
          (recur
            (dec spiral-size)
            (- direction)
            (concat mapping row column)
            (last column)))))))

(defn spiral [size]
  (if (zero? size)
    '()
    (->> (zipmap (mapping size) (indices size))
         (into (sorted-map))
         vals
         (partition-all size))))
