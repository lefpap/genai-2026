-- =========================================================
-- Stable demo entity IDs (identical on every fresh DB install)
-- =========================================================
-- document (java):  00000001-0000-4000-8000-000000000021
-- document (junior):00000001-0000-4000-8000-000000000022
-- document (fe):    00000001-0000-4000-8000-000000000023
-- document (devops):00000001-0000-4000-8000-000000000024
-- thread (java):    00000001-0000-4000-8000-000000000041
-- thread (devops):  00000001-0000-4000-8000-000000000042
-- message 1–4:      00000001-0000-4000-8000-000000000051 … 054

-- =========================================================
-- Documents
-- =========================================================

INSERT INTO document (id, title, content)
SELECT
    '00000001-0000-4000-8000-000000000021',
    'Senior Java Developer',
    'Own enterprise-scale Spring microservices modernization.

- Lead large-scale modernization projects
- Mentor engineers and ensure reliability KPIs
- Coordinate with product on roadmap delivery'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000021'
);

INSERT INTO document (id, title, content)
SELECT
    '00000001-0000-4000-8000-000000000022',
    'Junior Backend Developer',
    'Build internal APIs and automation tooling.

- Maintain Node.js services and pipelines
- Ship integrations with finance tooling
- Collaborate with DevOps for smoother releases'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000022'
);

INSERT INTO document (id, title, content)
SELECT
    '00000001-0000-4000-8000-000000000023',
    'Frontend Developer',
    'Deliver Next.js experiences for AI document flows.

- Build accessible UI for document comparisons
- Implement live previews for GenAI output
- Partner with design on rapid experiments'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000023'
);

INSERT INTO document (id, title, content)
SELECT
    '00000001-0000-4000-8000-000000000024',
    'DevOps Engineer',
    'Scale hybrid-cloud infra with IaC and observability.

- Own IaC modules for AWS/GCP rollout
- Improve telemetry and incident response
- Champion developer productivity workflows'
WHERE NOT EXISTS (
    SELECT 1 FROM document WHERE id = '00000001-0000-4000-8000-000000000024'
);

-- =========================================================
-- Chat threads
-- =========================================================

INSERT INTO chat_thread (id, title)
SELECT
    '00000001-0000-4000-8000-000000000041',
    'Senior Java discovery'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_thread WHERE id = '00000001-0000-4000-8000-000000000041'
);

INSERT INTO chat_thread (id, title)
SELECT
    '00000001-0000-4000-8000-000000000042',
    'DevOps hiring Q&A'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_thread WHERE id = '00000001-0000-4000-8000-000000000042'
);

-- =========================================================
-- Chat messages
-- =========================================================

INSERT INTO chat_message (id, thread_id, content)
SELECT
    '00000001-0000-4000-8000-000000000051',
    '00000001-0000-4000-8000-000000000041',
    'Can you summarize the senior Java posting for stakeholder updates?'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000051'
);

INSERT INTO chat_message (id, thread_id, content)
SELECT
    '00000001-0000-4000-8000-000000000052',
    '00000001-0000-4000-8000-000000000041',
    'The role focuses on modernizing Spring-based services and mentoring engineers.'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000052'
);

INSERT INTO chat_message (id, thread_id, content)
SELECT
    '00000001-0000-4000-8000-000000000053',
    '00000001-0000-4000-8000-000000000042',
    'What are the key outcomes for the DevOps engineer?'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000053'
);

INSERT INTO chat_message (id, thread_id, content)
SELECT
    '00000001-0000-4000-8000-000000000054',
    '00000001-0000-4000-8000-000000000042',
    'They will own IaC modules, telemetry improvements, and productivity tooling.'
WHERE NOT EXISTS (
    SELECT 1 FROM chat_message WHERE id = '00000001-0000-4000-8000-000000000054'
);
