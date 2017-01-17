(ns traversal.dev
  (:require [traversal.core :as traversal]
            [figwheel.client :as fw]))

(fw/start {:on-jsload traversal/run
           :websocket-url "ws://localhost:3449/figwheel-ws"})

