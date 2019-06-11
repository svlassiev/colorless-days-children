FROM nginx
COPY . /usr/share/nginx/html
RUN rm -fr /usr/share/nginx/html/colorless-days-children.iml && rm -fr /usr/share/nginx/html/.idea && rm -fr /usr/share/nginx/html/Dockerfile && rm -fr /usr/share/nginx/html/k8s