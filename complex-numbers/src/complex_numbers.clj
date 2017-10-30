(ns complex-numbers)

(defn real [[a b]] a)

(defn imaginary [[a b]] b)

(defn- abs-squared [[a b]]
  (+ (* a a) (* b b)))

(defn abs [[a b]]
  (Math/sqrt (abs-squared [a b])))

(defn conjugate [[a b]]
  [a (* b -1)])

(defn add [c1 c2]
  (apply vector (map + c1 c2)))

(defn sub [c1 c2]
  (apply vector (map - c1 c2)))

(defn mul [[a b] [c d]]
  [(- (* a c) (* b d))
   (+ (* a d) (* b c))])

(defn- divide-by-abs-squared [complex n]
  (let [abs-value (abs complex)]
    (/ n (* abs-value abs-value))))

(defn div [c1 c2]
  (->> (conjugate c2)
       (mul c1)
       (map (partial divide-by-abs-squared c2))
       (apply vector)))