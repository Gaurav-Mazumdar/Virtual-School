<?php

class DbOperations
{

    private $con;
    function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';

        $db = new DbConnect();
        $this->con = $db->connect();
    }

    public function createOrganisation($orgName, $estDate, $address, $contact, $email, $password, $orgHead, $run)
    {
        if (!$this->isEmailExixt($email)) {
            $stmt =  $this->con->prepare("INSERT INTO organisation(orgName, estDate, address, contact, email, password, orgHead, run) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("ssssssss", $orgName, $estDate, $address, $contact, $email, $password, $orgHead, $run);

            if ($stmt->execute()) {
                return USER_REGISTERED;
            } else {
                return USER_UNREGISTERED;
            }
        }
        return USER_EXIST;
    }

    public function orgLogin($email, $password)
    {
        if ($this->isEmailExixt($email)) {
            $hashPassword = $this->getPasswordByEmailId($email);
            if ($password == $hashPassword) {
                $status = $this->getStatusByEmailId($email);
                if ($status == "1") {
                    return USER_AUTHENTICATED;
                } else {
                    return USER_DEACTIVATED;
                }
            } else {
                return USER_PASSWORD_DO_NOT_MATCH;
            }
        }
        return USER_NOT_FOUND;
    }

    public function getOrgByEmail($email)
    {
        $stmt = $this->con->prepare("SELECT orgCode, orgName, estDate, address, contact, email, orgHead, status, live, run FROM organisation where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($orgCode, $orgName, $estDate, $address, $contact, $email, $orgHead, $status, $live, $run);
        $stmt->fetch();
        $organisation = array();
        $organisation['orgCode'] = $orgCode;
        $organisation['orgName'] = $orgName;
        $organisation['estDate'] = $estDate;
        $organisation['address'] = $address;
        $organisation['contact'] = $contact;
        $organisation['email'] = $email;
        $organisation['orgHead'] = $orgHead;
        $organisation['status'] = $status;
        $organisation['live'] = $live;
        $organisation['run'] = $run;

        return $organisation;
    }

    // ========================================================================================================================

    public function updateStatus($live, $email, $orgCode)
    {
        $stmt = $this->con->prepare("UPDATE organisation SET live = ?, email = ? where orgCode = ?");
        $stmt->bind_param("ssi", $live, $email, $orgCode);
        if ($stmt->execute()) {
            return true;
        } else {
            return false;
        }
    }

    public function getstatusByid($orgCode)
    {
        $stmt = $this->con->prepare("SELECT status FROM organisation where orgCode = ?");
        $stmt->bind_param("s", $orgCode);
        $stmt->execute();
        $stmt->bind_result($live);
        $stmt->fetch();
        $liveState = array();
        $liveState['live'] = $live;
        return $liveState;
    }

    private function isEmailExixt($email)
    {
        $stmt = $this->con->prepare("SELECT orgCode FROM organisation where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        return $stmt->num_rows > 0;
    }

    private function getPasswordByEmailId($email)
    {
        $stmt = $this->con->prepare("SELECT password FROM organisation where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($password);
        $stmt->fetch();
        return $password;
    }

    private function getStatusByEmailId($email)
    {
        $stmt = $this->con->prepare("SELECT status FROM organisation where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($status);
        $stmt->fetch();
        return $status;
    }

    // ========================================================================================================================

    public function createNewTeacher($orgCode, $name, $email, $password, $contact, $qualification, $address)
    {
        if (!$this->isTEmailExixt($email)) {
            $stmt =  $this->con->prepare("INSERT INTO teachers(orgCode, name, email, password, contact, qualification, address) VALUES (?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("sssssss", $orgCode, $name, $email, $password, $contact, $qualification, $address);

            if ($stmt->execute()) {
                return USER_REGISTERED;
            } else {
                return USER_UNREGISTERED;
            }
        }
        return USER_EXIST;
    }

    public function assignRole($orgCode, $teacherId, $teacherName, $assignedClass, $assignedSub, $periodNum, $dayVal)
    {
        $stmt =  $this->con->prepare("INSERT INTO teacherrole(orgCode, teacherId, teacherName, assignedClass, assignedSub, periodNum, dayVal) VALUES (?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("iisssss", $orgCode, $teacherId, $teacherName, $assignedClass, $assignedSub, $periodNum, $dayVal);

        if ($stmt->execute()) {
            return USER_REGISTERED;
        } else {
            return USER_UNREGISTERED;
        }
    }

    public function teacherLogin($email, $password)
    {
        if ($this->isTEmailExixt($email)) {
            $hashPassword = $this->getteacherPasswordByEmailId($email);
            if ($password == $hashPassword) {
                $status = $this->getStatusByTEmailId($email);
                if ($status == "1") {
                    return USER_AUTHENTICATED;
                } else {
                    return USER_DEACTIVATED;
                }
            } else {
                return USER_PASSWORD_DO_NOT_MATCH;
            }
        }
        return USER_NOT_FOUND;
    }

    public function createAssignment($tId, $std, $period, $subject, $topic, $link, $assignCode)
    {
        $stmt =  $this->con->prepare("INSERT INTO assignment(tId, std, period, subject, topic, link, assignCode) VALUES (?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("sssssss", $tId, $std, $period, $subject, $topic, $link, $assignCode);

        if ($stmt->execute()) {
            return USER_REGISTERED;
        } else {
            return USER_UNREGISTERED;
        }
    }

    public function isTEmailExixt($email)
    {
        $stmt = $this->con->prepare("SELECT teacherId FROM teachers where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        return $stmt->num_rows > 0;
    }

    /*public function isRoleCreated($orgCode, $teacherId)
    {
        $stmt = $this->con->prepare("SELECT id FROM teacherrole where = orgCode = ? and teacherId = ?");
        $stmt->bind_param("ii", $orgCode, $teacherId);
        $stmt->execute();
        $stmt->store_result();
        return $stmt->num_rows > 0;
    }*/

    public function getteacherPasswordByEmailId($email)
    {
        $stmt = $this->con->prepare("SELECT password FROM teachers where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($password);
        $stmt->fetch();
        return $password;
    }

    public function getTeacherByEmail($email)
    {
        $stmt = $this->con->prepare("SELECT teacherId, orgCode, name, email, contact, qualification, address, status, live FROM teachers where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($teacherId, $orgCode, $name, $email, $contact, $qualification, $address, $status, $live);
        $stmt->fetch();
        $fac = array();
        $fac['teacherId'] = $teacherId;
        $fac['orgCode'] = $orgCode;
        $fac['name'] = $name;
        $fac['email'] = $email;
        $fac['contact'] = $contact;
        $fac['qualification'] = $qualification;
        $fac['address'] = $address;
        $fac['status'] = $status;
        $fac['live'] = $live;

        return $fac;
    }

    public function getStatusByTEmailId($email)
    {
        $stmt = $this->con->prepare("SELECT status FROM teachers where email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($status);
        $stmt->fetch();
        return $status;
    }
}
