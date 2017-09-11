(ns sublist)

(defn within [inner outer]
  (or (empty? inner)
      (let [first-character (first inner)]
        (loop [candidate outer]
          (and (not (empty? candidate))
               (or (every? true? (map = inner candidate))
                   (recur (rest candidate))))))))

(defn classify [a b]
  (let [count-a (count a) count-b (count b)]
    (if (= count-a count-b)
      (if (= a b) :equal :unequal)
      (if (< count-a count-b)
        (if (within a b) :sublist :unequal)
        (if (within b a) :superlist :unequal)))))