import React, { useState, useRef } from 'react';
import { Modal, Button, Form, InputGroup } from 'react-bootstrap';
import './ChallengeBox.css';

function ChallengeBox({ title, description, type, icon: Icon }) {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [url, setUrl] = useState("");

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
                  <Form.Group controlId="formFile" className="mb-3">
                    <Form.Control type="file" />
                  </Form.Group>
                }
                {type === 'url' &&
                    <InputGroup className="mb-3">
                        <Form.Control
                          placeholder="Enter URL"
                          aria-label="Recipient's username"
                          aria-describedby="basic-addon2"
                        />
                        <Button variant="dark" id="button-addon2">
                          Submit
                        </Button>
                    </InputGroup>
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