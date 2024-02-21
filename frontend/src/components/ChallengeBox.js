import React, { useState, useEffect } from 'react';
import { Modal, Button, Form, InputGroup, Alert } from 'react-bootstrap';
import './ChallengeBox.css';
import Login from '../pages/Login.js'
//import Token from './Token.js'

function ChallengeBox({ title, description, type, icon: Icon }) {
    const [show, setShow] = useState(false);
    const [isLoading, setLoading] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);
    const [showError, setShowError] = useState(false);

    const [url, setUrl] = useState("");
    const [file, setFile] = useState("");

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        function simulateNetworkRequest() {
          return new Promise((resolve) => setTimeout(resolve, 2000));
        }

        if (isLoading) {
          simulateNetworkRequest().then(() => {
            setLoading(false);
          });
        }
    }, [isLoading]);

    const handleClick = () => setLoading(true);

    const handleUpload = async (e) =>{
        e.preventDefault();
        const formData = new FormData();
        formData.append('file', file)
        formData.append('fileName', file.name)
        try {
            const response = await fetch('http://localhost:8080/challenge/resume', {
                method: 'POST',
                body: formData
            });
            if(response.ok) {
//                console.log("success");
                setShowError(false);
                setShowSuccess(true);
            }
            else {
//                console.log("didn't work");
                setShowSuccess(false);
                setShowError(true);
            }
        }
        catch (error) {
//            console.log(error);
            setShowSuccess(false);
            setShowError(true);
        }
    }

    const handleUrl = async (e) => {
        e.preventDefault();
        if(title === "Coding Challenge"){
            try {
                const response = await fetch('http://localhost:8080/challenge/leetcodeSub?link=' + url, {
                    method: 'POST',
                    headers: {
                        'Authorization' : 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcnlubkBlcnlubi5jb20iLCJpYXQiOjE3MDg0OTU1ODYsImV4cCI6MTcwODU4MTk4Nn0.vmLhX43Vp4Vetvc422V6awXFDCN9s1VtsmEIuMWvGaY'
                    }
                });
                if (response.ok) {
//                    console.log("success");
                    setShowError(false);
                    setShowSuccess(true);
//                    console.log(token);
                }
                else {
                    setShowSuccess(false);
                    setShowError(true);
//                    console.log({token});
                }
            }
            catch (error) {
//                console.log(error);
                setShowSuccess(false);
                setShowError(true);
            }
        }
        if(title === "Earn a Certificate"){
            try {
                const response = await fetch('http://localhost:8080/challenge/linkedinCert?link=' + url, {
                    method: 'POST',
                    headers: {
                        'Authorization' : 'Bearer '
                    }
                });
//                console.log(Login.token);
                if (response.ok) {
//                    console.log("success");
                    setShowError(false);
                    setShowSuccess(true);
                }
                else {
                    setShowSuccess(false);
                    setShowError(true);
                }
            }
            catch (error) {
//                console.log(error);
                setShowSuccess(false);
                setShowError(true);
            }
        }
    }



    return (
        <div className="challengeDiv">
            <Button className="challengeBox" size="lg" variant="light" onClick={handleShow}>
                <div className="iconAndTitle">
                    <div className="icon">
                        {Icon && <Icon size={30} />}
                    </div>
                        <hr />
                    <div className="title">
                        {title}
                    </div>
                </div>
            </Button>

            <Modal
                centered
                show={show}
                onHide={handleClose}
            >
            <Modal.Header closeButton>
              <Modal.Title>{title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {description}
                {showSuccess && !isLoading &&
                    <Alert variant="primary">
                        Challenge completed! Please exit.
                    </Alert>
                }
                {showError && !isLoading &&
                    <Alert variant="danger">
                        Invalid submission. Please try again.
                    </Alert>
                }
            </Modal.Body>
            <Modal.Footer>
                {type === 'file' &&
                  <form onSubmit={(e) => {
                    handleUpload(e);
                    handleClick(e);
                  }}>
                      <InputGroup className="mb-3">
                        <Form.Control
                            type="file"
                            onChange={(e) => setFile(e.target.files[0])}
                        />
                        <Button
                            type="submit"
                            variant="dark"
                            disabled={isLoading}
                            id="button-addon2"
                        >
                          {isLoading ? 'Uploading' : 'Upload'}
                        </Button>
                      </InputGroup>
                  </form>
                }
                {type === 'url' &&
                    <form onSubmit={(e) => {
                        handleUrl(e);
                        handleClick(e);
                    }}>
                        <InputGroup className="mb-3">
                            <Form.Control
                              placeholder="Enter URL"
                              type="text"
                              value={url}
                              onChange={(e) => setUrl(e.target.value)}
                              aria-describedby="basic-addon2"
                            />
                            <Button
                                type="submit"
                                variant="dark"
                                disabled={isLoading}
                                id="button-addon2"
                            >
                              {isLoading ? 'Submiting' : 'Submit'}
                            </Button>
                        </InputGroup>
                    </form>
                }
                {type === null &&
                    <Button onClick={handleClose}>
                        Submit
                    </Button>
                }
            </Modal.Footer>
            </Modal>
        </div>
    );
}

export default ChallengeBox;