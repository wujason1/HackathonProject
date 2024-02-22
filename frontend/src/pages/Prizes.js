import { Row, Col, Container } from 'react-bootstrap';
import Sidenav from '../components/Sidenav';
import PrizeCard from '../components/PrizeCard.js';
import amazon from '../images/amazon.png';
import visa from '../images/visa.png';
import chipotle from '../images/chipotle.png';
import { useAuth } from '../components/AuthContext';

const Prizes = () => {
    const { isLoggedIn } = useAuth();

    // If not logged in, redirect to login page
     if (!isLoggedIn) {
        window.location.href = '/login';
        return null;
     }

  return (
    <div className="container-fluid">
        <div className="row">
            <div className="col-md-3">
                <Sidenav />
            </div>

          <div className="col-md-9">
            <main role="main" className="px-4">
              <div className="colorWhite">
                    <Container fluid="sm">
                          <Row>
                              <Col>
                                  <PrizeCard
                                      id={1}
                                      title={"Amazon Gift Card"}
                                      image={amazon}
                                  />
                              </Col>
                              <Col>
                                  <PrizeCard
                                      id={2}
                                      title={"Pre-paid Visa"}
                                      image={visa}
                                  />
                              </Col>
                              <Col>
                                  <PrizeCard
                                      id={3}
                                      title={"Chipotle Meal"}
                                      image={chipotle}
                                  />
                              </Col>
                          </Row>
                      </Container>
              </div>
            </main>
          </div>
        </div>
      </div>
  );
}

export default Prizes;