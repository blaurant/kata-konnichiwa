(ns kata-up&down)

;in a sequence like [1 3 5 2 4 10 9],
; 1,3,5 is up
; 5,2 is down
; 2,4,10 is up
; 10,9 is down
; result is [2 2] for 2 up and 2 down
;

(defn diff [prev last]
      (if (= prev last)
        :idem
        (if (< prev last)
          :up
          :down)))

(defn new-down? [prev first state]
      (let [diff (diff prev first)]
           (and (= :up state) (= :down diff))))

(defn new-up? [prev first state]
      (let [diff (diff prev first)]
           (and (= :down state) (= :up diff))))

;        :up            :down          :idem
; :up    :up            :down (:up+1)  :up
; :down  :up (:down+1)  :down          :down
; :idem  :up            :down          :idem

(defn add-last [up-accu down-accu state]
      (if (= :up state)
        [(inc up-accu) down-accu]
        (if (= :down state)
          [up-accu (inc down-accu)]
          [up-accu down-accu])))

(defn u&d [prev coll state up-accu down-accu]
      (println prev " " coll " " state " " up-accu " " down-accu)
      (if (empty? coll)
        (add-last up-accu down-accu state)
        (if (= :idem state)
          (u&d (first coll) (rest coll) (diff prev (first coll)) up-accu down-accu)
          (if (new-up? prev (first coll) state)
            (u&d (first coll) (rest coll) :up up-accu (inc down-accu))
            (if (new-down? prev (first coll) state)
              (u&d (first coll) (rest coll) :down (inc up-accu) down-accu)
              (u&d (first coll) (rest coll) state up-accu down-accu))))))

(defn up&down [coll]
      (if (< (count coll) 2)
        (throw (ex-info "bad input" {})))
      (u&d (first coll) (rest coll) (diff (first coll) (second coll)) 0 0))

(= (up&down [1 3 5 2 4 10 9]) [2 2])
(= (up&down [5 2 1]) [0 1])
(= (up&down [5 2 2 3 1]) [1 2])
(= (up&down [2 2 2 3 1]) [1 1])
(= (up&down [2 3]) [1 0])
(= (up&down [2 2 2 2 2]) [0 0])