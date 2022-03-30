# ds-sim server 14-Feb, 2022 @ MQ - client-server
# Server-side simulator started with './ds-server -c ds-sample-config02.xml -n -v all'
# Waiting for connection to port 50000 of IP address 127.0.0.1
RCVD HELO
SENT OK
RCVD AUTH some_random_auth_str
# Welcome  some_random_auth_str!
# The system information can be read from 'ds-system.xml'
SENT OK
RCVD REDY
SENT JOBN 37 0 1135 3 700 3800
RCVD GETS Capable 3 700 3800
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 inactive -1 16 32000 256000 0 0
xlarge 1 inactive -1 16 32000 256000 0 0
xlarge 2 inactive -1 16 32000 256000 0 0
xlarge 3 inactive -1 16 32000 256000 0 0
xlarge 4 inactive -1 16 32000 256000 0 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 0 xlarge 0
t:         37 job     0 (waiting) on # 0 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 60 1 597 1 700 1400
RCVD GETS Capable 1 700 1400
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 booting 97 13 31300 252200 1 0
xlarge 1 inactive -1 16 32000 256000 0 0
xlarge 2 inactive -1 16 32000 256000 0 0
xlarge 3 inactive -1 16 32000 256000 0 0
xlarge 4 inactive -1 16 32000 256000 0 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 1 xlarge 1
t:         60 job     1 (waiting) on # 1 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 96 2 1441 2 1500 2100
RCVD GETS Capable 2 1500 2100
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 booting 97 13 31300 252200 1 0
xlarge 1 booting 120 15 31300 254600 1 0
xlarge 2 inactive -1 16 32000 256000 0 0
xlarge 3 inactive -1 16 32000 256000 0 0
xlarge 4 inactive -1 16 32000 256000 0 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 2 xlarge 2
t:         96 job     2 (waiting) on # 2 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
t:         97 job     0 on # 0 of server xlarge RUNNING
SENT JOBN 101 3 954 2 900 2500
RCVD GETS Capable 2 900 2500
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 booting 120 15 31300 254600 1 0
xlarge 2 booting 156 14 30500 253900 1 0
xlarge 3 inactive -1 16 32000 256000 0 0
xlarge 4 inactive -1 16 32000 256000 0 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 3 xlarge 3
t:        101 job     3 (waiting) on # 3 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
t:        120 job     1 on # 1 of server xlarge RUNNING
SENT JOBN 137 4 5 1 100 2000
RCVD GETS Capable 1 100 2000
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 booting 156 14 30500 253900 1 0
xlarge 3 booting 161 14 31100 253500 1 0
xlarge 4 inactive -1 16 32000 256000 0 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 4 xlarge 4
t:        137 job     4 (waiting) on # 4 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 141 5 131 3 2700 2600
RCVD GETS Capable 3 2700 2600
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 booting 156 14 30500 253900 1 0
xlarge 3 booting 161 14 31100 253500 1 0
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 inactive -1 16 32000 256000 0 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 5 xlarge 5
t:        141 job     5 (waiting) on # 5 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 153 6 836 4 4000 7600
RCVD GETS Capable 4 4000 7600
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 booting 156 14 30500 253900 1 0
xlarge 3 booting 161 14 31100 253500 1 0
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 inactive -1 16 32000 256000 0 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 6 xlarge 6
t:        153 job     6 (waiting) on # 6 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
t:        156 job     2 on # 2 of server xlarge RUNNING
t:        161 job     3 on # 3 of server xlarge RUNNING
SENT JOBN 165 7 344 1 200 1000
RCVD GETS Capable 1 200 1000
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 inactive -1 16 32000 256000 0 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 7 xlarge 7
t:        165 job     7 (waiting) on # 7 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 169 8 235 1 100 800
RCVD GETS Capable 1 100 800
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 15 31800 255000 1 0
xlarge 8 inactive -1 16 32000 256000 0 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 8 xlarge 8
t:        169 job     8 (waiting) on # 8 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 169 9 591 1 300 700
RCVD GETS Capable 1 300 700
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 15 31800 255000 1 0
xlarge 8 booting 229 15 31900 255200 1 0
xlarge 9 inactive -1 16 32000 256000 0 0
RCVD OK
SENT .
RCVD SCHD 9 xlarge 9
t:        169 job     9 (waiting) on # 9 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 171 10 315 2 200 3200
RCVD GETS Capable 2 200 3200
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 15 31300 254600 0 1
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 15 31800 255000 1 0
xlarge 8 booting 229 15 31900 255200 1 0
xlarge 9 booting 229 15 31700 255300 1 0
RCVD OK
SENT .
## should have gone to xlarge 0 !!
RCVD SCHD 10 xlarge 1
t:        171 job    10 (running) on # 1 of server xlarge (active) SCHEDULED
t:        171 job    10 on # 1 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 173 11 26 1 500 600
RCVD GETS Capable 1 500 600
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 15 31900 254000 1 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 15 31800 255000 1 0
xlarge 8 booting 229 15 31900 255200 1 0
xlarge 9 booting 229 15 31700 255300 1 0
RCVD OK
SENT .
RCVD SCHD 11 xlarge 4
t:        173 job    11 (waiting) on # 4 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 175 12 5 2 1400 900
RCVD GETS Capable 2 1400 900
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 15 31800 255000 1 0
xlarge 8 booting 229 15 31900 255200 1 0
xlarge 9 booting 229 15 31700 255300 1 0
RCVD OK
SENT .
RCVD SCHD 12 xlarge 7
t:        175 job    12 (waiting) on # 7 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 177 13 149 3 1900 500
RCVD GETS Capable 3 1900 500
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 15 31900 255200 1 0
xlarge 9 booting 229 15 31700 255300 1 0
RCVD OK
SENT .
RCVD SCHD 13 xlarge 8
t:        177 job    13 (waiting) on # 8 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 177 14 220 4 3500 3900
RCVD GETS Capable 4 3500 3900
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 15 31700 255300 1 0
RCVD OK
SENT .
RCVD SCHD 14 xlarge 9
t:        177 job    14 (waiting) on # 9 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 180 15 467 1 1000 1700
RCVD GETS Capable 1 1000 1700
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 14 30500 253900 0 1
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 15 xlarge 2
t:        180 job    15 (running) on # 2 of server xlarge (active) SCHEDULED
t:        180 job    15 on # 2 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 181 16 2648 1 600 600
RCVD GETS Capable 1 600 600
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 13 29500 252200 0 2
xlarge 3 active 161 14 31100 253500 0 1
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 16 xlarge 3
t:        181 job    16 (running) on # 3 of server xlarge (active) SCHEDULED
t:        181 job    16 on # 3 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 185 17 1440 6 5700 10900
RCVD GETS Capable 6 5700 10900
SENT DATA 20 124
RCVD OK
SENT large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 13 29500 252200 0 2
xlarge 3 active 161 13 30500 252900 0 2
xlarge 4 booting 197 14 31400 253400 2 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 17 xlarge 4
t:        185 job    17 (waiting) on # 4 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 187 18 5 4 3200 6400
RCVD GETS Capable 4 3200 6400
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 13 31300 252200 0 1
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 13 29500 252200 0 2
xlarge 3 active 161 13 30500 252900 0 2
xlarge 4 booting 197 8 25700 242500 3 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 18 xlarge 0
t:        187 job    18 (running) on # 0 of server xlarge (active) SCHEDULED
t:        187 job    18 on # 0 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 190 19 292 4 3000 2200
RCVD GETS Capable 4 3000 2200
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 13 31100 251400 0 2
xlarge 2 active 156 13 29500 252200 0 2
xlarge 3 active 161 13 30500 252900 0 2
xlarge 4 booting 197 8 25700 242500 3 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 19 xlarge 1
t:        190 job    19 (running) on # 1 of server xlarge (active) SCHEDULED
t:        190 job    19 on # 1 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 194 20 88 3 3000 5100
RCVD GETS Capable 3 3000 5100
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 13 29500 252200 0 2
xlarge 3 active 161 13 30500 252900 0 2
xlarge 4 booting 197 8 25700 242500 3 0
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 20 xlarge 2
t:        194 job    20 (running) on # 2 of server xlarge (active) SCHEDULED
t:        194 job    20 on # 2 of server xlarge RUNNING
SENT OK
RCVD REDY
t:        197 job     4 on # 4 of server xlarge RUNNING
t:        197 job    11 on # 4 of server xlarge RUNNING
t:        197 job    17 on # 4 of server xlarge RUNNING
SENT JOBN 197 21 253 3 500 2800
RCVD GETS Capable 3 500 2800
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 13 30500 252900 0 2
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 booting 201 13 29300 253400 1 0
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 21 xlarge 3
t:        197 job    21 (running) on # 3 of server xlarge (active) SCHEDULED
t:        197 job    21 on # 3 of server xlarge RUNNING
SENT OK
RCVD REDY
t:        201 job     5 on # 5 of server xlarge RUNNING
SENT JOBN 201 22 2555 4 1100 900
RCVD GETS Capable 4 1100 900
SENT DATA 30 124
RCVD OK
SENT medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 13 29300 253400 0 1
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 22 xlarge 5
t:        201 job    22 (running) on # 5 of server xlarge (active) SCHEDULED
t:        201 job    22 on # 5 of server xlarge RUNNING
SENT OK
RCVD REDY
SENT JOBN 201 23 93447 2 500 1900
RCVD GETS Capable 2 500 1900
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 13 30400 254100 2 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 23 xlarge 7
t:        201 job    23 (waiting) on # 7 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 205 24 1005 15 13900 21200
RCVD GETS Capable 15 13900 21200
SENT DATA 10 124
RCVD OK
SENT xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 booting 213 12 28000 248400 1 0
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 24 xlarge 6
t:        205 job    24 (waiting) on # 6 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 207 25 1150 1 700 1800
RCVD GETS Capable 1 700 1800
SENT DATA 50 124
RCVD OK
SENT tiny 0 inactive -1 1 2000 16000 0 0
tiny 1 inactive -1 1 2000 16000 0 0
tiny 2 inactive -1 1 2000 16000 0 0
tiny 3 inactive -1 1 2000 16000 0 0
tiny 4 inactive -1 1 2000 16000 0 0
tiny 5 inactive -1 1 2000 16000 0 0
tiny 6 inactive -1 1 2000 16000 0 0
tiny 7 inactive -1 1 2000 16000 0 0
tiny 8 inactive -1 1 2000 16000 0 0
tiny 9 inactive -1 1 2000 16000 0 0
small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 booting 213 12 28000 248400 2 0
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 25 xlarge 6
t:        207 job    25 (waiting) on # 6 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 211 26 9 8 7800 13500
RCVD GETS Capable 8 7800 13500
SENT DATA 20 124
RCVD OK
SENT large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 booting 213 12 28000 248400 3 0
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 26 xlarge 6
t:        211 job    26 (waiting) on # 6 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 212 27 2140 2 1600 1400
RCVD GETS Capable 2 1600 1400
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 booting 213 12 28000 248400 4 0
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 27 xlarge 6
t:        212 job    27 (waiting) on # 6 of server xlarge (booting) SCHEDULED
SENT OK
RCVD REDY
t:        213 job     6 on # 6 of server xlarge RUNNING
SENT JOBN 216 28 2455 2 600 1400
RCVD GETS Capable 2 600 1400
SENT DATA 40 124
RCVD OK
SENT small 0 inactive -1 2 4000 32000 0 0
small 1 inactive -1 2 4000 32000 0 0
small 2 inactive -1 2 4000 32000 0 0
small 3 inactive -1 2 4000 32000 0 0
small 4 inactive -1 2 4000 32000 0 0
small 5 inactive -1 2 4000 32000 0 0
small 6 inactive -1 2 4000 32000 0 0
small 7 inactive -1 2 4000 32000 0 0
small 8 inactive -1 2 4000 32000 0 0
small 9 inactive -1 2 4000 32000 0 0
medium 0 inactive -1 4 8000 64000 0 0
medium 1 inactive -1 4 8000 64000 0 0
medium 2 inactive -1 4 8000 64000 0 0
medium 3 inactive -1 4 8000 64000 0 0
medium 4 inactive -1 4 8000 64000 0 0
medium 5 inactive -1 4 8000 64000 0 0
medium 6 inactive -1 4 8000 64000 0 0
medium 7 inactive -1 4 8000 64000 0 0
medium 8 inactive -1 4 8000 64000 0 0
medium 9 inactive -1 4 8000 64000 0 0
large 0 inactive -1 8 16000 128000 0 0
large 1 inactive -1 8 16000 128000 0 0
large 2 inactive -1 8 16000 128000 0 0
large 3 inactive -1 8 16000 128000 0 0
large 4 inactive -1 8 16000 128000 0 0
large 5 inactive -1 8 16000 128000 0 0
large 6 inactive -1 8 16000 128000 0 0
large 7 inactive -1 8 16000 128000 0 0
large 8 inactive -1 8 16000 128000 0 0
large 9 inactive -1 8 16000 128000 0 0
xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 active 213 12 28000 248400 4 1
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 28 xlarge 6
t:        216 job    28 (waiting) on # 6 of server xlarge (active) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 220 29 17 9 8200 16700
RCVD GETS Capable 9 8200 16700
SENT DATA 10 124
RCVD OK
SENT xlarge 0 active 97 9 28100 245800 0 2
xlarge 1 active 120 9 28100 249200 0 3
xlarge 2 active 156 10 26500 247100 0 3
xlarge 3 active 161 10 30000 250100 0 3
xlarge 4 active 197 8 25700 242500 0 3
xlarge 5 active 201 9 28200 252500 0 2
xlarge 6 active 213 12 28000 248400 5 1
xlarge 7 booting 225 11 29900 252200 3 0
xlarge 8 booting 229 12 30000 254700 2 0
xlarge 9 booting 229 11 28200 251400 2 0
RCVD OK
SENT .
RCVD SCHD 29 xlarge 6
t:        220 job    29 (waiting) on # 6 of server xlarge (active) SCHEDULED
SENT OK
RCVD REDY
t:        225 job     7 on # 7 of server xlarge RUNNING
t:        225 job    12 on # 7 of server xlarge RUNNING
t:        225 job    23 on # 7 of server xlarge RUNNING
t:        229 job     8 on # 8 of server xlarge RUNNING
t:        229 job    13 on # 8 of server xlarge RUNNING
t:        229 job     9 on # 9 of server xlarge RUNNING
t:        229 job    14 on # 9 of server xlarge RUNNING
t:        231 job    12 on # 7 of server xlarge COMPLETED
SENT JCPL 231 12 xlarge 7
RCVD REDY
t:        234 job    18 on # 0 of server xlarge COMPLETED
SENT JCPL 234 18 xlarge 0
RCVD REDY
t:        305 job     4 on # 4 of server xlarge COMPLETED
SENT JCPL 305 4 xlarge 4
RCVD REDY
t:        331 job    10 on # 1 of server xlarge COMPLETED
SENT JCPL 331 10 xlarge 1
RCVD REDY
t:        351 job    11 on # 4 of server xlarge COMPLETED
SENT JCPL 351 11 xlarge 4
RCVD REDY
t:        360 job     5 on # 5 of server xlarge COMPLETED
SENT JCPL 360 5 xlarge 5
RCVD REDY
t:        363 job    21 on # 3 of server xlarge COMPLETED
SENT JCPL 363 21 xlarge 3
RCVD REDY
t:        448 job     8 on # 8 of server xlarge COMPLETED
SENT JCPL 448 8 xlarge 8
RCVD REDY
t:        469 job    14 on # 9 of server xlarge COMPLETED
SENT JCPL 469 14 xlarge 9
RCVD REDY
t:        485 job    19 on # 1 of server xlarge COMPLETED
SENT JCPL 485 19 xlarge 1
RCVD REDY
t:        514 job     7 on # 7 of server xlarge COMPLETED
SENT JCPL 514 7 xlarge 7
RCVD REDY
t:        526 job     9 on # 9 of server xlarge COMPLETED
SENT JCPL 526 9 xlarge 9
RCVD REDY
t:        633 job    13 on # 8 of server xlarge COMPLETED
SENT JCPL 633 13 xlarge 8
RCVD REDY
t:        662 job    20 on # 2 of server xlarge COMPLETED
SENT JCPL 662 20 xlarge 2
RCVD REDY
t:        673 job    15 on # 2 of server xlarge COMPLETED
SENT JCPL 673 15 xlarge 2
RCVD REDY
t:        674 job     1 on # 1 of server xlarge COMPLETED
SENT JCPL 674 1 xlarge 1
RCVD REDY
t:        976 job     6 on # 6 of server xlarge COMPLETED
t:        976 job    24 on # 6 of server xlarge RUNNING
t:        976 job    25 on # 6 of server xlarge RUNNING
SENT JCPL 976 6 xlarge 6
RCVD REDY
t:       1315 job     0 on # 0 of server xlarge COMPLETED
SENT JCPL 1315 0 xlarge 0
RCVD REDY
t:       1376 job    17 on # 4 of server xlarge COMPLETED
SENT JCPL 1376 17 xlarge 4
RCVD REDY
t:       1559 job    24 on # 6 of server xlarge COMPLETED
t:       1559 job    26 on # 6 of server xlarge RUNNING
t:       1559 job    27 on # 6 of server xlarge RUNNING
t:       1559 job    28 on # 6 of server xlarge RUNNING
SENT JCPL 1559 24 xlarge 6
RCVD REDY
t:       1573 job    26 on # 6 of server xlarge COMPLETED
t:       1573 job    29 on # 6 of server xlarge RUNNING
SENT JCPL 1573 26 xlarge 6
RCVD REDY
t:       1593 job    29 on # 6 of server xlarge COMPLETED
SENT JCPL 1593 29 xlarge 6
RCVD REDY
t:       1641 job    16 on # 3 of server xlarge COMPLETED
SENT JCPL 1641 16 xlarge 3
RCVD REDY
t:       1685 job    22 on # 5 of server xlarge COMPLETED
SENT JCPL 1685 22 xlarge 5
RCVD REDY
t:       1726 job     3 on # 3 of server xlarge COMPLETED
SENT JCPL 1726 3 xlarge 3
RCVD REDY
t:       1752 job    25 on # 6 of server xlarge COMPLETED
SENT JCPL 1752 25 xlarge 6
RCVD REDY
t:       1944 job     2 on # 2 of server xlarge COMPLETED
SENT JCPL 1944 2 xlarge 2
RCVD REDY
t:       2950 job    28 on # 6 of server xlarge COMPLETED
SENT JCPL 2950 28 xlarge 6
RCVD REDY
t:       7598 job    27 on # 6 of server xlarge COMPLETED
SENT JCPL 7598 27 xlarge 6
RCVD REDY
t:      84003 job    23 on # 7 of server xlarge COMPLETED
SENT JCPL 84003 23 xlarge 7
RCVD REDY
SENT NONE
RCVD QUIT
SENT QUIT
# -------------------------------------------------------------------------------------
# 0 tiny servers used with a utilisation of 0.00 at the cost of $0.00
# 0 small servers used with a utilisation of 0.00 at the cost of $0.00
# 0 medium servers used with a utilisation of 0.00 at the cost of $0.00
# 0 large servers used with a utilisation of 0.00 at the cost of $0.00
# 10 xlarge servers used with a utilisation of 100.00 at the cost of $44.29
# ==================================== [ Summary ] ====================================
# actual simulation end time: 84003, #jobs: 30 (failed 0 times)
# total #servers used: 10, avg util: 100.00% (ef. usage: 100.00%), total cost: $44.29
# avg waiting time: 258, avg exec time: 3537, avg turnaround time: 3795
