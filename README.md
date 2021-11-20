# Converter Pdf
It was just supposed to be a pdf conversion application, and I ended up using it to test the scheduler with shedlock, LoL.
- Test with scheduler with multiple instances in kubernetes using with shedlock

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
1. Package application jar
```
.\mvnw clean install
```
2. Generate docker image
```
.\mvnw dockerfile:build
```
3. Set config map with values database
```
kubectl create -f .\kube\config-map.yaml
```
5. Create deployment of application in kubernetes
```
kubectl create -f .\deployment.yaml
```

## Screenshot:

![alt text](https://github.com/sampaioops/converter-pdf/blob**/**main/image_test_shedlock.png?raw=true)
