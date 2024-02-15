import React, { useState } from 'react';
import { Modal, Button, Form, InputGroup } from 'react-bootstrap';
import './ChallengeBox.css';

function ChallengeBox({ title, description, type, icon: Icon }) {
    const [show, setShow] = useState(false);
    const [url, setUrl] = useState("");
    const [file, setFile] = useState("");

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

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
                // successful submission message
                // close dialog
                console.log("success");
            }
            else {
                // show error message
                console.log("didn't work");
            }
        }
        catch (error) {
            console.log(error);
        }
    }

    const handleUrl = async (e) => {
        e.preventDefault();
        if(title === "Coding Challenge"){
            try {
                const response = await fetch('http://localhost:8080/challenge/leetcodeSub?link=' + url, {
                    method: 'POST'
                });
                if (response.ok) {
                    // successful submission message
                    // close dialog
                    console.log("success");
                }
                else {
                    // show error message
                }
            }
            catch (error) {
                console.log(error);
            }
        }
        if(title === "Earn a Certificate"){
            try {
                const response = await fetch('http://localhost:8080/challenge/linkedinCert?link=' + url, {
                    method: 'POST'
                });
                if (response.ok) {
                    // successful submission message
                    // close dialog
                    console.log("success")
                }
                else {
                    // show error message
                }
            }
            catch (error) {
                console.log(error);
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
            <Modal.Body>{description}</Modal.Body>
            <Modal.Footer>
                {type === 'file' &&
                  <form onSubmit={handleUpload}>
                      <InputGroup className="mb-3">
                        <Form.Control
                            type="file"
                            onChange={(e) => setFile(e.target.files[0])}
                        />
                        <Button type="submit" variant="dark" id="button-addon2">
                          Upload
                        </Button>
                      </InputGroup>
                  </form>
                }
                {type === 'url' &&
                    <form onSubmit={handleUrl}>
                        <InputGroup className="mb-3">
                            <Form.Control
                              placeholder="Enter URL"
                              type="text"
                              value={url}
                              onChange={(e) => setUrl(e.target.value)}
                              aria-describedby="basic-addon2"
                            />
                            <Button type="submit" variant="dark" id="button-addon2">
                              Submit
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