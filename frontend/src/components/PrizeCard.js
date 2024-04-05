import { useState, useEffect } from 'react';
import { Card, Button, Toast, ToastContainer } from 'react-bootstrap';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

function PrizeCard({id, title, image}) {
  const [active, setActive] = useState(false);
  const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
      Challenge not completed. Please go to Challenges page to complete Challenge.
    </Tooltip>
  );
  const [show, setShow] = useState(false);

  const toggleShow = () => setShow(!show);

    useEffect(() => {
        fetch('http://18.223.21.3:8080/prizes/' + id, {
            method: 'GET',
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem('token')
            },
        }).then(response => response.json())
        .then(data => setActive(data.message))
      }, []);

  return (
    <>
        <Card style={{ width: '18rem' }}>
          <Card.Img variant="top" src={image} width={100} height={180}/>
          <Card.Body>
            <Card.Title>{title}</Card.Title>
            {!active &&
                <OverlayTrigger
                  placement="right"
                  delay={{ show: 250, hide: 400 }}
                  overlay={renderTooltip}
                >
                    <span className="d-inline-block">
                        <Button variant="dark" disabled style={{ pointerEvents: 'none' }}>Redeem</Button>
                    </span>
                </OverlayTrigger>
            }
            {active &&
                <Button variant="light" onClick={toggleShow}>Redeem</Button>
            }
          </Card.Body>
        </Card>
        <ToastContainer>
            <Toast show={show} onClose={toggleShow} autohide>
                <Toast.Body style={{color: 'black'}}>Congratulations! Prize redeemed and sent to your email.</Toast.Body>
            </Toast>
        </ToastContainer>
    </>
  );
}
export default PrizeCard;