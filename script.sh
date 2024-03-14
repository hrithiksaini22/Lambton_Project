#!/bin/bash
minikube start --driver docker
kubectl apply -f deployment.yaml
sleep 20
kubectl port-forward svc/todolistapp-service 3000:3000 --address 0.0.0.0 &
sleep 150
kubectl delete -f deployment.yaml
minikube stop

