(ns space-age)

(def seconds-in-earth-year 31557600)

(def orbit-period-ratios
  {:earth   1.0
   :mercury 0.2408467
   :venus   0.61519726
   :mars    1.8808158
   :jupiter 11.862615
   :saturn  29.447498
   :uranus  84.016846
   :neptune 164.79132})

(defn on [planet seconds]
  (let [in-earth-years (/ seconds seconds-in-earth-year)]
    (/ in-earth-years (planet orbit-period-ratios))))

(defn on-earth [seconds]
  (on :earth seconds))

(defn on-mercury [seconds]
  (on :mercury seconds))

(defn on-venus [seconds]
  (on :venus seconds))

(defn on-mars [seconds]
  (on :mars seconds))

(defn on-jupiter [seconds]
  (on :jupiter seconds))

(defn on-saturn [seconds]
  (on :saturn seconds))

(defn on-uranus [seconds]
  (on :uranus seconds))

(defn on-neptune [seconds]
  (on :neptune seconds))