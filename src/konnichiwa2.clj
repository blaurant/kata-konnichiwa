(ns konnichiwa2)


(def code [{"n" "ん"}
           {"wo" "を",
            "zo" "ぞ",
            "ku" "く",
            "ne" "ね",
            "mo" "も",
            "ro" "ろ",
            "pe" "ぺ",
            "ji" "じ",
            "nu" "ぬ",
            "ze" "ぜ",
            "po" "ぽ",
            "ya" "や",
            "zu" "ず",
            "bo" "ぼ",
            "ki" "き",
            "pu" "ぷ",
            "mi" "み",
            "bu" "ぶ",
            "sa" "さ",
            "bi" "び",
            "pi" "ぴ",
            "ma" "ま",
            "ni" "に",
            "wa" "わ",
            "go" "ご",
            "gu" "ぐ",
            "da" "だ",
            "de" "で",
            "ri" "り",
            "hi" "ひ",
            "so" "そ",
            "ru" "る",
            "ha" "は",
            "ga" "が",
            "be" "べ",
            "se" "せ",
            "ba" "ば",
            "ge" "げ",
            "do" "ど",
            "za" "ざ",
            "mu" "む",
            "ra" "ら",
            "ke" "け",
            "no" "の",
            "re" "れ",
            "ho" "ほ",
            "gi" "ぎ",
            "ko" "こ",
            "te" "て",
            "ka" "か",
            "su" "す",
            "na" "な",
            "to" "と",
            "pa" "ぱ",
            "fu" "ふ",
            "ta" "た",
            "he" "へ",
            "me" "め",
            "yu" "ゆ",
            "yo" "よ"}
           {"tsu" "つ",
            "chi" "ち",
            "shi" "し"}])

(defn n-head [word n]
      (subs word 0 n))

(defn n-rest [word n]
      (subs word n (count word)))

(defn size-cap [word cap]
      (let [size (count word)]
           (if (< size cap)
             size
             cap)))

(defn break-code [word n]
      (if (= n 0)
        ["" ""]
        (if-let [deco (get (nth code (- n 1)) (n-head word n))]
                [(n-rest word n) deco]
                (break-code word (- n 1)))))

(defn decode [word]
      (if (zero? (count word))
        ""
        (let [length (size-cap word 3)
              [rest deco] (break-code word length)]
             (str deco (decode rest)))))




; ANOTHER SOLUTION
;
;(def code {"n"   "ん",
;           "wo"  "を",
;           "zo"  "ぞ",
;           "ku"  "く",
;           "ne"  "ね",
;           "mo"  "も",
;           "ro"  "ろ",
;           "pe"  "ぺ",
;           "ji"  "じ",
;           "nu"  "ぬ",
;           "ze"  "ぜ",
;           "po"  "ぽ",
;           "ya"  "や",
;           "zu"  "ず",
;           "tsu" "つ",
;           "bo"  "ぼ",
;           "ki"  "き",
;           "pu"  "ぷ",
;           "shi" "し",
;           "mi"  "み",
;           "bu"  "ぶ",
;           "sa"  "さ",
;           "bi"  "び",
;           "pi"  "ぴ",
;           "ma"  "ま",
;           "ni"  "に",
;           "wa"  "わ",
;           "go"  "ご",
;           "gu"  "ぐ",
;           "chi" "ち",
;           "da"  "だ",
;           "de"  "で",
;           "ri"  "り",
;           "hi"  "ひ",
;           "so"  "そ",
;           "ru"  "る",
;           "ha"  "は",
;           "ga"  "が",
;           "be"  "べ",
;           "se"  "せ",
;           "ba"  "ば",
;           "ge"  "げ",
;           "do"  "ど",
;           "za"  "ざ",
;           "mu"  "む",
;           "ra"  "ら",
;           "ke"  "け",
;           "no"  "の",
;           "re"  "れ",
;           "ho"  "ほ",
;           "gi"  "ぎ",
;           "ko"  "こ",
;           "te"  "て",
;           "ka"  "か",
;           "su"  "す",
;           "na"  "な",
;           "to"  "と",
;           "pa"  "ぱ",
;           "fu"  "ふ",
;           "ta"  "た",
;           "he"  "へ",
;           "me"  "め",
;           "yu"  "ゆ",
;           "yo"  "よ"})
;
;(def code-keys ["tsu"
;                "shi"
;                "chi"
;                "wo"
;                "zo"
;                "ku"
;                "ne"
;                "mo"
;                "ro"
;                "pe"
;                "ji"
;                "nu"
;                "ze"
;                "po"
;                "ya"
;                "zu"
;                "bo"
;                "ki"
;                "pu"
;                "mi"
;                "bu"
;                "sa"
;                "bi"
;                "pi"
;                "ma"
;                "ni"
;                "wa"
;                "go"
;                "gu"
;                "da"
;                "de"
;                "ri"
;                "hi"
;                "so"
;                "ru"
;                "ha"
;                "ga"
;                "be"
;                "se"
;                "ba"
;                "ge"
;                "do"
;                "za"
;                "mu"
;                "ra"
;                "ke"
;                "no"
;                "re"
;                "ho"
;                "gi"
;                "ko"
;                "te"
;                "ka"
;                "su"
;                "na"
;                "to"
;                "pa"
;                "fu"
;                "ta"
;                "he"
;                "me"
;                "yu"
;                "yo"
;                "n"])
;
;(defn match [k word-fr]
;  (let [m (re-matches (re-pattern (str k "(.*)")) word-fr)]
;    (if m
;      [k (second m)])))
;; (match "ko" "konnichiwa")
;;=> ["ko" "nnichiwa"]
;
;
;(defn split [word-fr]
;  (->> code-keys
;       (map #(match % word-fr))
;       (filter some?)
;       first))
;;(split "konnichiwa")
;;=> ["ko" "nnichiwa"]
;
;
;(defn split-jp [word-fr]
;  (if (or (nil? word-fr) (empty? word-fr))
;    '()
;    (let [splited (split word-fr)]
;      (println splited)
;      (if (nil? splited)
;        (throw (ex-info (format "no translation for %s" word-fr) {}))
;        (cons (first splited) (split-jp (second splited)))))))
;;(split-jp "konnichiwa")
;;[ko nnichiwa]
;;[n nichiwa]
;;[ni chiwa]
;;[chi wa]
;;[wa ]
;;=> ("ko" "n" "ni" "chi" "wa")
;
;
;(defn translate-to-jp [word-fr]
;  (clojure.string/join (map code (split-jp word-fr))))
;;(translate-to-jp "konnichiwa")
;;[ko nnichiwa]
;;[n nichiwa]
;;[ni chiwa]
;;[chi wa]
;;[wa ]
;;=> "こんにちわ"
;
;
;(= (translate-to-jp "") "")
;(= (translate-to-jp "k") "")
;(= (translate-to-jp "ko") "こ")
;(= (translate-to-jp "konn") "こん")
;(= (translate-to-jp "konni") "こんに")
;(= (translate-to-jp "konnichiwa") "こんにちわ")
;


; ANOTHER SOLUTION
;
;(defn str2vect [str]
;  (if (empty? str) [] (split str #"")))
;
;(defn rest-after-n [vect n]
;  (take-last (- (count vect) n) vect))
;
;(def codec {["k" "o"]     "か",
;            ["n"]         "ん",
;            ["n" "i"]     "に",
;            ["c" "h" "i"] "ち",
;            ["w" "a"]     "わ"})
;
;(defn is-syl-lenght [letters n]
;  (if (codec (take n letters))
;    n))
;
;(defn find-syl-lenght [letters]
;  (or (and (>= (count letters) 3) (is-syl-lenght letters 3))
;      (and (>= (count letters) 2) (is-syl-lenght letters 2))
;      (and (>= (count letters) 1) (is-syl-lenght letters 1))))
;
;(defn translate
;  ([word] (translate (str2vect word) ""))
;  ([letters, res]
;   (if (empty? letters)
;     res
;     (let [syl-size (find-syl-lenght letters)]
;       (if (not syl-size)
;         res
;         (translate (rest-after-n letters syl-size) (str res (codec (take syl-size letters)))))))))
;
;(= (translate "") "")
;(= (translate "k") "")
;(= (translate "ko") "か")
;(= (translate "konn") "かんん")
;(= (translate "konni") "かんに")
;(= (translate "plouf") "")
;(= (translate "konpatapouf") "かん")                          ;todo
;(= (translate "konnichiwa") "かんにちわ")