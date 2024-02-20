import { Card, Button } from 'react-bootstrap';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

function PrizeCard({active, title, image}) {
  const renderTooltip = (props) => (
    <Tooltip id="button-tooltip" {...props}>
      Challenge not completed. Please go to Challenges page to complete Challenge.
    </Tooltip>
  );

  return (
    <>
        <Card style={{ width: '18rem' }}>
          <Card.Img variant="top" src={image} width={100} height={180}/>
          <Card.Body>
            <Card.Title>{title}</Card.Title>
            {active === false &&
                <OverlayTrigger
                  placement="right"
                  delay={{ show: 250, hide: 400 }}
                  overlay={renderTooltip}
                >
                    <Button variant="dark" active="false">Redeem</Button>
                </OverlayTrigger>
            }
            {active === true &&
                <Button variant="light">Redeem</Button>
            }
          </Card.Body>
        </Card>
    </>
  );
}
export default PrizeCard;