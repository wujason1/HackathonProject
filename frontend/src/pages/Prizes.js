import { Row, Col, Container } from 'react-bootstrap';
import PrizeCard from '../components/PrizeCard.js';
import amazon from '../images/amazon.png';
import visa from '../images/visa.png';
import chipotle from '../images/chipotle.png';

const Prizes = () => {

  return (
  <Container fluid="sm">
        <Row>
            <Col>
                <PrizeCard
                    active={false}
                    title={"Amazon Gift Card"}
                    image={amazon}
                />
            </Col>
            <Col>
                <PrizeCard
                    active={true}
                    title={"Pre-paid Visa"}
                    image={visa}
                />
            </Col>
            <Col>
                <PrizeCard
                    active={true}
                    title={"Chipotle Meal"}
                    image={chipotle}
                />
            </Col>
        </Row>
    </Container>
  );
}

export default Prizes;