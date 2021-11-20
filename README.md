# Converter Pdf
 Testing Schedule of multi instance in kubernetes with ShedLock

### Setup
1. minikube: https://minikube.sigs.k8s.io/docs/start/
2. kubectl: https://kubernetes.io/docs/tasks/tools/
3. docker: https://docs.docker.com/

#### Get started:
1. Initialize kubernetes
```
minikube start
```
```
minikube dashboard
```
2. Postgres deployment in kubernetes
```
kubectl create -f .\kube\values-postgres.yaml
```
```
kubectl create -f .\kube\service-postgres.yaml
```

### Application deployment in kubernetes
1. package application jar
```
.\mvnw clean install
```
2. generate docker image
```
.\mvnw dockerfile:build
```
3. Set config map with values database
```
kubectl create -f .\kube\config-map.yaml
```
5. create deployment of application in kubernetes
```
kubectl create -f .\deployment.yaml
```
