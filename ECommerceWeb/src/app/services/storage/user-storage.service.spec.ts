import { TestBed } from '@angular/core/testing';

import { UserStorgeService } from './user-storage.service';

describe('UserStorgeService', () => {
  let service: UserStorgeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserStorgeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
