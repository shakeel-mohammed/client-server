# ds-sim server 14-Feb, 2022 @ MQ - client-server
# Server-side simulator started with './ds-server -c ds-sample-config01.xml -v all'
# Waiting for connection to port 50000 of IP address 127.0.0.1
RCVD HELO
SENT OK
RCVD AUTH parallels
# Welcome  parallels!
# The system information can be read from 'ds-system.xml'
SENT OK
RCVD REDY
SENT JOBN 37 0 653 3 700 3800
RCVD GETS Capable 3 700 3800
SENT DATA 3 124
RCVD OK
SENT joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 inactive -1 16 64000 512000 0 0
RCVD OK
SENT .
RCVD SCHD 0 super-silk 0
t:         37 job     0 (waiting) on # 0 of server super-silk (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 60 1 2025 2 1500 2900
RCVD GETS Capable 2 1500 2900
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 booting 117 13 63300 508200 1 0
RCVD OK
SENT .
RCVD SCHD 1 super-silk 0
t:         60 job     1 (waiting) on # 0 of server super-silk (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 96 2 343 2 1500 2100
RCVD GETS Capable 2 1500 2100
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 booting 117 11 61800 505300 2 0
RCVD OK
SENT .
RCVD SCHD 2 super-silk 0
t:         96 job     2 (waiting) on # 0 of server super-silk (booting) SCHEDULED
SENT OK
RCVD REDY
SENT JOBN 101 3 380 2 900 2500
RCVD GETS Capable 2 900 2500
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 booting 117 9 60300 503200 3 0
RCVD OK
SENT .
RCVD SCHD 3 super-silk 0
t:        101 job     3 (waiting) on # 0 of server super-silk (booting) SCHEDULED
SENT OK
RCVD REDY
t:        117 job     0 on # 0 of server super-silk RUNNING
t:        117 job     1 on # 0 of server super-silk RUNNING
t:        117 job     2 on # 0 of server super-silk RUNNING
t:        117 job     3 on # 0 of server super-silk RUNNING
SENT JOBN 137 4 111 1 100 2000
RCVD GETS Capable 1 100 2000
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 7 59400 500700 0 4
RCVD OK
SENT .
RCVD SCHD 4 super-silk 0
t:        137 job     4 (running) on # 0 of server super-silk (active) SCHEDULED
t:        137 job     4 on # 0 of server super-silk RUNNING
SENT OK
RCVD REDY
SENT JOBN 156 5 8 3 2700 2600
RCVD GETS Capable 3 2700 2600
SENT DATA 3 124
RCVD OK
SENT joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 6 59300 498700 0 5
RCVD OK
SENT .
RCVD SCHD 5 super-silk 0
t:        156 job     5 (running) on # 0 of server super-silk (active) SCHEDULED
t:        156 job     5 on # 0 of server super-silk RUNNING
SENT OK
RCVD REDY
t:        177 job     5 on # 0 of server super-silk COMPLETED
SENT JCPL 177 5 super-silk 0
RCVD REDY
SENT JOBN 198 6 1074 4 4000 7600
RCVD GETS Capable 4 4000 7600
SENT DATA 3 124
RCVD OK
SENT joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 6 59300 498700 0 5
RCVD OK
SENT .
RCVD SCHD 6 super-silk 0
t:        198 job     6 (running) on # 0 of server super-silk (active) SCHEDULED
t:        198 job     6 on # 0 of server super-silk RUNNING
SENT OK
RCVD REDY
SENT JOBN 225 7 442 2 500 2100
RCVD GETS Capable 2 500 2100
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 2 55300 491100 0 6
RCVD OK
SENT .
RCVD SCHD 7 super-silk 0
t:        225 job     7 (running) on # 0 of server super-silk (active) SCHEDULED
t:        225 job     7 on # 0 of server super-silk RUNNING
SENT OK
RCVD REDY
SENT JOBN 249 8 926 1 100 800
RCVD GETS Capable 1 100 800
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 0 54800 489000 0 7
RCVD OK
SENT .
RCVD SCHD 8 super-silk 0
t:        249 job     8 (waiting) on # 0 of server super-silk (active) SCHEDULED
SENT OK
RCVD REDY
t:        303 job     4 on # 0 of server super-silk COMPLETED
t:        303 job     8 on # 0 of server super-silk RUNNING
SENT JCPL 303 4 super-silk 0
RCVD REDY
SENT JOBN 308 9 2010 2 600 1500
RCVD GETS Capable 2 600 1500
SENT DATA 5 124
RCVD OK
SENT juju 0 inactive -1 2 4000 16000 0 0
juju 1 inactive -1 2 4000 16000 0 0
joon 0 inactive -1 4 16000 64000 0 0
joon 1 inactive -1 4 16000 64000 0 0
super-silk 0 active 117 0 54800 490200 0 7
RCVD OK
SENT .
RCVD SCHD 9 super-silk 0
t:        308 job     9 (waiting) on # 0 of server super-silk (active) SCHEDULED
SENT OK
RCVD REDY
t:        575 job     7 on # 0 of server super-silk COMPLETED
t:        575 job     9 on # 0 of server super-silk RUNNING
SENT JCPL 575 7 super-silk 0
RCVD REDY
t:        603 job     2 on # 0 of server super-silk COMPLETED
SENT JCPL 603 2 super-silk 0
RCVD REDY
t:        758 job     6 on # 0 of server super-silk COMPLETED
SENT JCPL 758 6 super-silk 0
RCVD REDY
t:       1127 job     8 on # 0 of server super-silk COMPLETED
SENT JCPL 1127 8 super-silk 0
RCVD REDY
t:       1188 job     3 on # 0 of server super-silk COMPLETED
SENT JCPL 1188 3 super-silk 0
RCVD REDY
t:       1212 job     1 on # 0 of server super-silk COMPLETED
SENT JCPL 1212 1 super-silk 0
RCVD REDY
t:       1357 job     0 on # 0 of server super-silk COMPLETED
SENT JCPL 1357 0 super-silk 0
RCVD REDY
t:       2045 job     9 on # 0 of server super-silk COMPLETED
SENT JCPL 2045 9 super-silk 0
RCVD REDY
SENT NONE
RCVD QUIT
SENT QUIT
# -------------------------------------------------------------------------------------
# 0 juju servers used with a utilisation of 0.00 at the cost of $0.00
# 0 joon servers used with a utilisation of 0.00 at the cost of $0.00
# 1 super-silk servers used with a utilisation of 100.00 at the cost of $0.43
# ==================================== [ Summary ] ====================================
# actual simulation end time: 2045, #jobs: 10 (failed 0 times)
# total #servers used: 1, avg util: 100.00% (ef. usage: 100.00%), total cost: $0.43
