FROM ubuntu:latest
RUN apt update
RUN apt install nginx -y
RUN rm /etc/nginx/sites-available/default
RUN rm /etc/nginx/sites-enabled/default
ADD nginx.conf /etc/nginx/sites-available
ADD nginx.conf /etc/nginx/sites-enabled
ADD web /home
CMD /usr/sbin/nginx -g "daemon off;"