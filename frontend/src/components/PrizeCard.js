import { useState, useEffect } from 'react';
import { Card, Button } from 'react-bootstrap';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

function PrizeCard({id, title, image}) {
  const [active, setActive] = useState(false);
  const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
      Challenge not completed. Please go to Challenges page to complete Challenge.
    </Tooltip>
  );

    useEffect(() => {
        fetch('http://localhost:8080/prizes/' + id, {
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
                    <Button variant="dark" active="false">Redeem</Button>
                </OverlayTrigger>
            }
            {active &&
                <Button variant="light">Redeem</Button>
            }
          </Card.Body>
        </Card>
    </>
  );
}
export default PrizeCard;