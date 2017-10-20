(ns robot-simulator)

(def directions
  {:north {:left :west, :right :east}
   :east {:left :north, :right :south}
   :south {:left :east, :right :west}
   :west {:left :south, :right :north}})

(def translations
  {:north {:x 0, :y 1}
   :east {:x 1, :y 0}
   :south {:x 0, :y -1}
   :west {:x -1, :y 0}})

(defn robot [coordinates bearing]
  (hash-map :coordinates coordinates, :bearing bearing))

(defn turn-right [bearing]
  (-> directions bearing :right))

(defn turn-left [bearing]
  (-> directions bearing :left))

(defn- translate [{x :x, y :y}, bearing]
  (let [{delta-x :x, delta-y :y} (translations bearing)]
    (hash-map :x (+ x delta-x), :y (+ y delta-y))))

(defn- do-step [{coordinates :coordinates, bearing :bearing} next-step]
  (case next-step
    \A (robot (translate coordinates bearing) bearing)
    \L (robot coordinates (turn-left bearing))
    \R (robot coordinates (turn-right bearing))))

(defn simulate [steps robot]
  (reduce do-step robot steps))
