(ns bracket-push)

(def bracket-pairs
  {\( \),
   \{ \},
   \[ \]})

(defn- match? [closing-bracket opening-bracket]
  (= closing-bracket (bracket-pairs opening-bracket)))

(defn- push [stack val]
  (conj stack val))

(defn- handle-closing [stack closing-bracket]
  (if (match? closing-bracket (peek stack))
    (pop stack)
    (push stack closing-bracket)))

(defn- opening? [character]
  (contains? (set (keys bracket-pairs)) character))

(defn- closing? [character]
  (contains? (set (vals bracket-pairs)) character))

(defn- track-brackets [stack character]
  (or (cond
        (opening? character) (push stack character)
        (closing? character) (handle-closing stack character))
      stack))

(defn valid? [expr]
  (empty? (reduce track-brackets [] expr)))